package ru.re1coded.cpw.item.weapon;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class MurphyItem extends SwordItem {
    public MurphyItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(MobEffect.byId(18), 100, 1, false, false, true), pTarget);
        pAttacker.addEffect(new MobEffectInstance(MobEffect.byId(10), 100, 0, false, false, true), pAttacker);
        pAttacker.addEffect(new MobEffectInstance(MobEffect.byId(11), 100, 1, false, false, true), pAttacker);
        pAttacker.addEffect(new MobEffectInstance(MobEffect.byId(5), 100, 1, false, false, true), pAttacker);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
