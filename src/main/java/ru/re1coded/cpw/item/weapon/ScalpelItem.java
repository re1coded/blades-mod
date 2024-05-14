package ru.re1coded.cpw.item.weapon;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class ScalpelItem extends SwordItem {
    public ScalpelItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(MobEffect.byId(20), 10, 0, false, false, false), pTarget);
        pTarget.addEffect(new MobEffectInstance(MobEffect.byId(9), 10, 1, false, false, false), pTarget);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
