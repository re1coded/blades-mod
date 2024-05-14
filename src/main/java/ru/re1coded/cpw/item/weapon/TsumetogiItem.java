package ru.re1coded.cpw.item.weapon;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

//Tsumetogi special properties
public class TsumetogiItem extends SwordItem {
    public TsumetogiItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    //Adding effects on entities
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(MobEffect.byId(20), 30, 0, false, false, false), pTarget);
        pTarget.addEffect(new MobEffectInstance(MobEffect.byId(9), 30, 0, false, false, false), pTarget);
        pAttacker.addEffect(new MobEffectInstance(MobEffect.byId(11), 50, 1, false, false, true), pAttacker);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
