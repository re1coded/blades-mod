package ru.re1coded.cpw.item.weapon;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class CleaverItem extends SwordItem {
    public CleaverItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        double rand = Math.random();
        if(rand <= 0.3) {
            pTarget.addEffect(new MobEffectInstance(MobEffects.POISON, 30, 0, false, false, true));
        }
        if(pTarget.hasEffect(MobEffects.POISON)) {
            pAttacker.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 0, false, false, true));
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
