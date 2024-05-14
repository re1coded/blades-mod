package ru.re1coded.cpw.item.weapon;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;


//Errata special properties
public class ErrataItem extends SwordItem {

    public ErrataItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.setSecondsOnFire(10);
        if (pTarget.isOnFire()) {
            pAttacker.addEffect(new MobEffectInstance(MobEffect.byId(5), 100, 2, false, false, false), pAttacker);
        }
        /*if(!pAttacker.level().isClientSide()) {
            ((ServerLevel)pAttacker.level()).sendParticles(ParticleTypes.CRIT, pTarget.getX(), pTarget.getY(), pTarget.getZ(), 10, 0.1d, 0.1d, 0.1d, 0.2d);
        }*/
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }


}
