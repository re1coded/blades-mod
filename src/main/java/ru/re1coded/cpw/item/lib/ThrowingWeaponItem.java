package ru.re1coded.cpw.item.lib;

import com.google.common.collect.Multimap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import ru.re1coded.cpw.entity.ModEntities;
import ru.re1coded.cpw.entity.projectile.ThrowingWeaponEntity;


public class ThrowingWeaponItem extends SwordItem {
    public static final String NBT_AMMO_USED = "AmmoUsed";
    public static final String NBT_UUID = "UUID";
    public static final String NBT_ORIGINAL = "Original";

    protected float attackDamage = 1.0f;
    protected double attackSpeed = 0.0D;
    protected float throwVelocity = 2.0f;
    protected float throwDamageMultiplier = 2.0f;
    //protected WeaponMaterial material;
    protected String customDisplayName = null;
    protected boolean doCraftCheck = true;
    protected boolean canBeCrafted = true;
    protected int maxAmmo = 1;
    protected int maxChargeTicks = 5;
    protected Multimap<Attribute, AttributeModifier> modifiers;
    //protected final WeaponArchetype archetype;

    //protected List<WeaponTrait> traits;

    public ThrowingWeaponItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    public void damageThrowingWeapon(ItemStack stack, int damage, LivingEntity entity) {
        //stack.damageItem(damage, entity);
        if(stack.isDamageableItem() && stack.getOrCreateTag().getInt(NBT_AMMO_USED) < maxAmmo &&
                (!(entity instanceof Player) || !((Player)entity).getAbilities().instabuild))
        {
            if(stack.hurt(damage, entity.getRandom(), entity instanceof ServerPlayer ? (ServerPlayer)entity : null))
            {
                InteractionHand breakHand = stack == entity.getOffhandItem() ? InteractionHand.OFF_HAND : stack == entity.getMainHandItem() ? InteractionHand.MAIN_HAND : null;
                if(breakHand != null)
                    entity.broadcastBreakEvent(breakHand);

                int ammo = stack.getTag().getInt(NBT_AMMO_USED);
                stack.getTag().putInt(NBT_AMMO_USED, ++ammo);

                if(entity instanceof Player)
                {
                    ((Player)entity).awardStat(Stats.ITEM_BROKEN.get(stack.getItem()));
                }

                stack.setDamageValue(0);
            }
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        /*if(stack.getOrCreateTag().getInt(NBT_AMMO_USED) == maxAmmo){
            return InteractionResultHolder.fail(stack);
        }*/
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        if(pLivingEntity instanceof Player) {
            int ammoCount = maxAmmo - 1;

            /*if(ammoCount > 0)
            {*/
            Player player = (Player) pLivingEntity;

            int maxCharge = maxChargeTicks;
            int charge = Math.min(getUseDuration(pStack) - pTimeCharged, maxCharge);

            if (!pLevel.isClientSide && charge > 2) {
                ThrowingWeaponEntity thrown = createThrowingWeaponEntity(pLevel, player, pStack, charge);
                float chargePerc = (charge / (float) maxCharge);

                if (thrown == null) return;

                thrown.setWeapon(pStack);
                thrown.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, throwVelocity * 1.2f * (chargePerc * 0.9f + 0.1f), 0.5f);

                double damageMultiplier = (throwDamageMultiplier - 1.0f) * chargePerc + 1.0f;
                thrown.setBaseDamage((getDirectAttackDamage() + 8.0d) * damageMultiplier);

                if (player.getAbilities().instabuild)
                    thrown.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                else if (thrown.isValidThrowingWeapon()) {
                    ammoCount--;
                    pStack.getOrCreateTag().putInt(NBT_AMMO_USED, maxAmmo - ammoCount);

                    // If there is no ammo left and the stack isn't original (picked up from the ground to create a new stack), then delete the stack
                    /*if (ammoCount == 0 && !pStack.getTag().getBoolean(NBT_ORIGINAL)) {
                        pStack.shrink(1);
                        if (pStack.getCount() <= 0)
                            player.getInventory().removeItem(pStack);
                    }*/
                }

                if (thrown.isValidThrowingWeapon()) {
                    pStack.setDamageValue(0);
                    pLevel.playSound((Player) null, player.getX(), player.getY(), player.getZ(), getThrowingSound(), SoundSource.PLAYERS, 0.5F, 0.4F / (pLevel.random.nextFloat() * 0.4F + 0.8F));
                    pLevel.addFreshEntity(thrown);
                }
                pStack.shrink(1);
                player.awardStat(Stats.ITEM_USED.get(this));
            }
            //}
        }
        super.releaseUsing(pStack, pLevel, pLivingEntity, pTimeCharged);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack)
    {
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack stack)
    {
        return 720; //it was 72000
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment)
    {
        // Allow Loyalty enchantments to work on Throwing Weapons
        return super.canApplyAtEnchantingTable(stack, enchantment) || enchantment == Enchantments.LOYALTY;
    }

    public float getDirectAttackDamage()
    {
        return attackDamage;
    }

    public void setAttackSpeed(double speed)
    {
        attackSpeed = speed;
    }

    public void setChargeTicks(int chargeTicks)
    {
        maxChargeTicks = chargeTicks;
    }

    protected SoundEvent getThrowingSound()
    {
        return SoundEvents.TRIDENT_THROW;
    }

    public int getMaxAmmo() {return maxAmmo;}

    public ThrowingWeaponEntity createThrowingWeaponEntity(Level levelIn, Player player, ItemStack stack, int charge)
    {
        return new ThrowingWeaponEntity(ModEntities.THROWING_WEAPON.get(), player, levelIn);
    }
}
