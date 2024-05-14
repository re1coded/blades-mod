package ru.re1coded.cpw.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.re1coded.cpw.BladeMod;
import ru.re1coded.cpw.entity.projectile.ThrowingKnifeEntity;
import ru.re1coded.cpw.entity.projectile.ThrowingWeaponEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BladeMod.MOD_ID);

    //This is also an ObliviousSpartan code.
    //https://github.com/ObliviousSpartan/SpartanWeaponry
    public static final RegistryObject<EntityType<ThrowingWeaponEntity>> THROWING_WEAPON = ENTITIES.register("throwing_weapon", () -> EntityType.Builder.<ThrowingWeaponEntity>of(ThrowingWeaponEntity::new, MobCategory.MISC).
            clientTrackingRange(4).
            updateInterval(20).
            setShouldReceiveVelocityUpdates(true).
            sized(0.5f, 0.5f).
            setCustomClientFactory(ThrowingWeaponEntity::new).
            build("throwing_weapon"));

    public static final RegistryObject<EntityType<ThrowingKnifeEntity>> THROWING_KNIFE = ENTITIES.register("throwing_knife", () -> EntityType.Builder.<ThrowingKnifeEntity>of(ThrowingKnifeEntity::new, MobCategory.MISC).
            clientTrackingRange(4).
            updateInterval(20).
            setShouldReceiveVelocityUpdates(true).
            sized(0.5f, 0.5f).
            setCustomClientFactory(ThrowingKnifeEntity::new).
            build("throwing_knife"));


    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
