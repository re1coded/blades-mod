package ru.re1coded.cpw;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import ru.re1coded.cpw.block.ModBlocks;
import ru.re1coded.cpw.block.entity.ModBlockEntities;
import ru.re1coded.cpw.entity.ModEntities;
import ru.re1coded.cpw.item.ModCreativeMenuTabs;
import ru.re1coded.cpw.item.ModItems;
import ru.re1coded.cpw.loot.ModLootModifiers;
import ru.re1coded.cpw.recipe.ModRecipes;
import ru.re1coded.cpw.item.client.renderer.ThrowingWeaponRenderer;
import ru.re1coded.cpw.screen.ModMenuTypes;
import ru.re1coded.cpw.screen.PrinterScreen;
import ru.re1coded.cpw.sound.ModSounds;
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BladeMod.MOD_ID)
public class BladeMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "cpw";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public BladeMod() {

        GeckoLib.initialize();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModCreativeMenuTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModEntities.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModSounds.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
    
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.PRINTER_MENU.get(), PrinterScreen::new);

            EntityRenderers.register(ModEntities.THROWING_WEAPON.get(), ThrowingWeaponRenderer::new);
            EntityRenderers.register(ModEntities.THROWING_KNIFE.get(), ThrowingWeaponRenderer::new);
        }
    }
}
