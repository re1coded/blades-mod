package ru.re1coded.cpw.entity.projectile;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;
import ru.re1coded.cpw.entity.ModEntities;

public class ThrowingKnifeEntity extends ThrowingWeaponEntity {
    public ThrowingKnifeEntity(EntityType<? extends ThrowingWeaponEntity> type, Level level) {
        super(type, level);
    }

    public ThrowingKnifeEntity(Level level, LivingEntity pShooter) {
        super(ModEntities.THROWING_KNIFE.get(), pShooter, level);
    }

    public ThrowingKnifeEntity(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this(ModEntities.THROWING_KNIFE.get(), level);
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }
}
