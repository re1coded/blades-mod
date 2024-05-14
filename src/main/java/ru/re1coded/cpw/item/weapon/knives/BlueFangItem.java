package ru.re1coded.cpw.item.weapon.knives;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class BlueFangItem extends ThrowingKnifeItem{
    public BlueFangItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 40, 2, false, false, true));
        double rand = Math.random();
        if(rand <= 0.2) {
            pTarget.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 1, false, false, true));
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
