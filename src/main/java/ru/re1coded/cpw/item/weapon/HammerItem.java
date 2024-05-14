package ru.re1coded.cpw.item.weapon;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class HammerItem extends SwordItem {
    public HammerItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        //guaranteed stun!
        pTarget.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0, false, false, true));
        double x = Math.random();
        if(x <= 0.3) { // simulate critical hit
            pTarget.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 10, 0, false, false, true));
            pTarget.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 50, 1, false, false, true));
            pAttacker.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 0, false, false, true));
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
