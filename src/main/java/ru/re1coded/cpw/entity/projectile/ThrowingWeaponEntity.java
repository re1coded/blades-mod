package ru.re1coded.cpw.entity.projectile;

/*
    HUUUUUUGE thanks to ObliviousSpartan and his Mod "Spartan Weaponry" on GitHub
    https://github.com/ObliviousSpartan/SpartanWeaponry
    that is his code from forge 1.19.2, and I think, it works
 */

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import ru.re1coded.cpw.entity.ModEntities;
import ru.re1coded.cpw.item.lib.ThrowingWeaponItem;

public class ThrowingWeaponEntity extends AbstractArrow implements IEntityAdditionalSpawnData {
    public static final String NBT_WEAPON = "Weapon";
    protected static final EntityDataAccessor<ItemStack> DATA_WEAPON = SynchedEntityData.defineId(ThrowingWeaponEntity.class, EntityDataSerializers.ITEM_STACK);
    protected static final EntityDataAccessor<Byte> DATA_RETURN = SynchedEntityData.defineId(ThrowingWeaponEntity.class, EntityDataSerializers.BYTE);
    protected int ticksInAir = 0;
    protected float waterInertia = 0.0f;
    protected boolean isReturning = false;
    protected boolean playedReturnSound = false;
    private int life;



    public ThrowingWeaponEntity(EntityType<? extends ThrowingWeaponEntity> type, Level level) {
        super(type, level);
    }

    public ThrowingWeaponEntity(EntityType<? extends ThrowingWeaponEntity> type, Level level, double x, double y, double z) {
        super(type , x, y, z, level);
    }

    public ThrowingWeaponEntity(EntityType<? extends ThrowingWeaponEntity> type, LivingEntity shooter, Level level) {
        super(type, shooter, level);
    }

    public ThrowingWeaponEntity(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this(ModEntities.THROWING_WEAPON.get(), level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(DATA_WEAPON, ItemStack.EMPTY);
        getEntityData().define(DATA_RETURN, (byte)0);
    }

    @Override
    protected ItemStack getPickupItem() {
        return getEntityData().get(DATA_WEAPON);
    }

    public boolean isReturning() {
        return isReturning;
    }

    @Override
    public void tick() {
        Entity thrower = getOwner();			//func_234616_v_();
        if((inGroundTime > 60 || isReturning) && thrower != null) {

            int returnLevel = 1; //getEntityData().get(DATA_RETURN);
            if(returnLevel > 0) {

                if(thrower != null && thrower.isAlive() && (!(thrower instanceof ServerPlayer) || !thrower.isSpectator())) {

                    // Return to thrower
                    if(!isReturning) {

                        setNoPhysics(true);
                        inGround = false;
                        isReturning = true;
                        setNoGravity(true);

                    }
                    Vec3 distance = new Vec3(thrower.getX() - getX(), thrower.getEyeY() - getY(), thrower.getZ() - getZ());
                    setPosRaw(getX(), getY() + distance.y * 0.015 * (double)returnLevel, getZ());
                    if(level().isClientSide) {
                        yOld = getY();
                    }

                    double velocity = 2.0d + 0.25d * (double)returnLevel;
                    setDeltaMovement(getDeltaMovement().scale(0.95).add(distance.normalize().scale(velocity)));

                    if(!playedReturnSound) {
                        playSound(SoundEvents.TRIDENT_THROW, 10.0F, 1.0F);
                        playedReturnSound = true;
                    }
                }
                else if(returnLevel > 0 && !thrower.isAlive()) {
                    setNoPhysics(false);
                    isReturning = false;
                    setNoGravity(false);
                    getEntityData().set(DATA_RETURN, (byte)0);
                }
            }
        }

        super.tick();

        if(!inGround)
            ++ticksInAir;
        else if(ticksInAir != 0)
            ticksInAir = 0;
    }

    @Override
    protected void tickDespawn() {
        ++life;
        if (life >= 1200)
        {
            dropAsItem();
            discard();
        }
    }

    @Override
    public int getPortalWaitTime() {
        // Set this time higher to prevent this entity from being duplicated when hitting a portal...
        return 100;
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        Entity entity = hitResult.getEntity();
        Entity shooter = getOwner();    //func_234616_v_();

        if(entity != null) {

            ItemStack weapon = getWeaponItem();
            float damage = Mth.ceil(getBaseDamage()) * 2;
            DamageSource damagesource;

            if (isCritArrow()) {

                damage += random.nextInt((int)damage / 2 + 2);
            }

            // Try and catch the throwing weapon if possible.
            if(shooter != null && (canBeCaughtInMidair(shooter, entity) || isReturning) && entity instanceof Player player) {

                if(attemptCatch(player))
                    return;
            }
            if (shooter == null) {
                damagesource = this.damageSources().arrow(this, this);
            } else {
                damagesource = this.damageSources().arrow(this, shooter);
                if (shooter instanceof LivingEntity) {
                    ((LivingEntity)shooter).setLastHurtMob(entity);
                }
            }

            boolean isEnderman = entity.getType() == EntityType.ENDERMAN;
            if (isOnFire() && !isEnderman) {
                entity.setSecondsOnFire(5);
            }

            if(entity.hurt(damagesource, damage)) {

                if(weapon.isDamageableItem() && weapon.hurt(1, random, null)) {

                    playSound(SoundEvents.ITEM_BREAK, 0.8f, 0.8f + random.nextFloat() * 0.4f);
                    level().broadcastEntityEvent(this, (byte)3);
                    discard();
                }

                if (entity instanceof LivingEntity entitylivingbase) {

                    //LivingEntity entitylivingbase = (LivingEntity)entity; CHANGED

                    if (getKnockback() > 0) {

                        Vec3 knockVec = getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double)getKnockback() * 0.6D);

                        if (knockVec.lengthSqr() > 0.0F)
                            entitylivingbase.push(knockVec.x, 0.1d, knockVec.z);
                    }

                    doPostHurtEffects(entitylivingbase);

                    if (shooter != null && entitylivingbase != shooter && entitylivingbase instanceof Player && shooter instanceof ServerPlayer) {

                        ((ServerPlayer)shooter).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
                    }
                }

                playSound(SoundEvents.ARROW_HIT, 1.0F, 1.2F / (random.nextFloat() * 0.2F + 0.9F));

                if (!isEnderman) {

                    setDeltaMovement(getDeltaMovement().scale(-0.1d));
                    setYRot(getYRot() + 180.0f);
                    yRotO += 180.0F;
                }
            } else {
                setDeltaMovement(getDeltaMovement().scale(-0.1d));
                setYRot(getYRot() + 180.0f);
                yRotO += 180.0F;
                ticksInAir = 0;

                if (!level().isClientSide && getDeltaMovement().lengthSqr() < 1.0e-7d) {

                    if(getEntityData().get(DATA_RETURN) > 0 && !isNoPhysics())
                        setNoPhysics(true);
                    else if (pickup == Pickup.ALLOWED) {

                        dropAsItem();
                        discard();
                    }
                }
            }
        } else {
            super.onHitEntity(hitResult);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        if(hitResult.getType() == HitResult.Type.BLOCK) {

            if(!level().isClientSide) {

                BlockState state = level().getBlockState(hitResult.getBlockPos());
                ((ServerLevel)level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, state).setPos(hitResult.getBlockPos()), getX(), getY(), getZ(), 5, 0.1d, 0.1d, 0.1d, 0.05d);
            }

            ItemStack stack = getWeaponItem();
            removeEnchantments(stack);
        }
        super.onHitBlock(hitResult);
    }

    /**
     * Delete any enchantments from the provided item stack to prevent duping of said enchantments
     * @param stack The item stack to remove enchantments from
     */
    protected void removeEnchantments(ItemStack stack) {
        if(stack.isEnchanted() && stack.getOrCreateTag().contains(ThrowingWeaponItem.NBT_AMMO_USED))
        {
            EnchantmentHelper.setEnchantments(ImmutableMap.of(), stack);

            // Spawn magic dispersal particles
            if(!level().isClientSide)
                ((ServerLevel)level()).sendParticles(ParticleTypes.WITCH, getX(), getY(), getZ(), 10, 0.1d, 0.1d, 0.1d, 0.2d);
        }
    }

    protected void dropAsItem() {
        ItemStack stack = getWeaponItem();
        removeEnchantments(stack);
        spawnAtLocation(stack, 0.1F);
    }

    @Override
    public void playerTouch(Player entityIn) {
        if(inGround || isReturning)
            attemptCatch(entityIn);
    }

    @Override
    protected float getWaterInertia() {
        return waterInertia > 0.0f ? waterInertia : super.getWaterInertia();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
    }

    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        buffer.writeItemStack(getWeaponItem(), false);
    }

    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {
        setWeapon(additionalData.readItem());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public ItemStack getWeaponItem() {
        return getPickupItem();
    }

    public boolean isValidThrowingWeapon() {
        return !getWeaponItem().isEmpty();
    }

    public void setWeapon(ItemStack weaponStack) {
        ItemStack stack = weaponStack.copy();
        if(!stack.isEmpty() && stack.getItem() instanceof ThrowingWeaponItem)
        {
            int maxAmmo = ((ThrowingWeaponItem)stack.getItem()).getMaxAmmo();
            stack.getOrCreateTag().putInt(ThrowingWeaponItem.NBT_AMMO_USED, maxAmmo - 1);
            stack.getTag().putBoolean(ThrowingWeaponItem.NBT_ORIGINAL, false);
        }
        getEntityData().set(DATA_WEAPON, stack);
        getEntityData().set(DATA_RETURN, (byte)stack.getEnchantmentLevel(Enchantments.LOYALTY));
    }

    protected boolean canBeCaughtInMidair(Entity shooter, Entity entityHit) {
        return shooter == entityHit && isNoPhysics();
    }

    public int getTicksInAir() {
        return ticksInAir;
    }

    protected boolean attemptCatch(Player player) {
        if(!level().isClientSide && shakeTime <= 0)
        {
            boolean canBePickedUp = pickup == AbstractArrow.Pickup.ALLOWED || pickup == AbstractArrow.Pickup.CREATIVE_ONLY;
            boolean pickUpAsNewItem = true;
            ItemStack weapon = getWeaponItem();

            if(pickup == AbstractArrow.Pickup.ALLOWED) {

                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    ItemStack slotStack = player.getInventory().getItem(i);
                    if (slotStack.getItem() == weapon.getItem() &&
                            weapon.getOrCreateTag().hasUUID(ThrowingWeaponItem.NBT_UUID) && slotStack.getOrCreateTag().hasUUID(ThrowingWeaponItem.NBT_UUID) &&
                            weapon.getTag().getUUID(ThrowingWeaponItem.NBT_UUID).equals(slotStack.getTag().getUUID(ThrowingWeaponItem.NBT_UUID)) &&
                            weapon.getItem() instanceof ThrowingWeaponItem throwingWeapon) {
                        int maxAmmo = throwingWeapon.getMaxAmmo();
                        int currentAmmo = maxAmmo - slotStack.getTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED);

                        if (currentAmmo < maxAmmo) {
                            int itemDamage = slotStack.getDamageValue() + weapon.getDamageValue();

                            // If the total damage exceeds the damage of the equipped stack, then "break" one of the ammo items and not increment the ammo count
                            if (itemDamage > slotStack.getMaxDamage()) {
                                itemDamage -= slotStack.getMaxDamage() + 1;
                            } else {
                                currentAmmo++;
                                slotStack.getTag().putInt(ThrowingWeaponItem.NBT_AMMO_USED, maxAmmo - currentAmmo);
                            }
                            slotStack.setDamageValue(itemDamage);
                            pickUpAsNewItem = false;
                            canBePickedUp = true;
                            break;
                        }
                    }
                }
            }
                if(pickUpAsNewItem)
                {
                    ItemStack pickUpStack = weapon.copy();
                    removeEnchantments(pickUpStack);
                    canBePickedUp = player.getInventory().add(pickUpStack);
                }


            if(canBePickedUp)
            {
                player.take(this, 1);
                discard();
            }

            return canBePickedUp;
        }
        return false;
    }
}
