package ru.re1coded.cpw.item.weapon;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import java.util.Objects;

//Jinchu Maru special properties
public class JinchuMaruItem extends SwordItem {
    public JinchuMaruItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pAttacker.hasEffect(Objects.requireNonNull(MobEffect.byId(14)))) {
            pAttacker.addEffect(new MobEffectInstance(Objects.requireNonNull(MobEffect.byId(5)), 100, 2, false, false, false), pAttacker);
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
