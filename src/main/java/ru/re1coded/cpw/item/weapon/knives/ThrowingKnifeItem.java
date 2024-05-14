package ru.re1coded.cpw.item.weapon.knives;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import ru.re1coded.cpw.entity.projectile.ThrowingKnifeEntity;
import ru.re1coded.cpw.entity.projectile.ThrowingWeaponEntity;
import ru.re1coded.cpw.item.lib.ThrowingWeaponItem;

public class ThrowingKnifeItem extends ThrowingWeaponItem {
    public ThrowingKnifeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public ThrowingWeaponEntity createThrowingWeaponEntity(Level levelIn, Player player, ItemStack stack, int charge) {
        return new ThrowingKnifeEntity(levelIn, player);
    }

    @Override
    protected SoundEvent getThrowingSound() {
        return SoundEvents.TRIDENT_THROW;
    }
}
