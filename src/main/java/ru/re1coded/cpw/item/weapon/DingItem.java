package ru.re1coded.cpw.item.weapon;


import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

//DING DING special properties
public class DingItem extends SwordItem {
    public DingItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }


    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        double x = Math.random(); //hope this works
        if(x <= 0.3 && !(x == 0.1) ) {
            //30% chance to get slowness, miningFatique, weakness (= shocking)
            pTarget.addEffect(new MobEffectInstance(MobEffect.byId(2), 500, 2, false, false, false));
            pTarget.addEffect(new MobEffectInstance(MobEffect.byId(4), 500, 2, false, false, false));
            pTarget.addEffect(new MobEffectInstance(MobEffect.byId(18), 1000, 2, false, false, true));
        } else if (x == 0.1) {
            //10% chance to get critical hit
            pTarget.addEffect(new MobEffectInstance(MobEffect.byId(2), 1000, 3, false, false, false));
            pTarget.addEffect(new MobEffectInstance(MobEffect.byId(4), 1000, 3, false, false, false));
            pTarget.addEffect(new MobEffectInstance(MobEffect.byId(18), 2000, 3, false, false, true));
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
