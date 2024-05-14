package ru.re1coded.cpw.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import ru.re1coded.cpw.BladeMod;
import ru.re1coded.cpw.block.ModBlocks;

@SuppressWarnings("unused")
public class ModCreativeMenuTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BladeMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CPW_TAB = CREATIVE_MODE_TABS.register("cpw_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STONE_MESH.get()))
                    .title(Component.translatable("creativetab.cpw.tab"))
                    .displayItems(((pParameters, pOutput) -> {
                        //armor
                        pOutput.accept(ModItems.PUNK_JACKET.get());
                        pOutput.accept(ModItems.SUIT_HELMET.get());
                        pOutput.accept(ModItems.SUIT_CHESTPLATE.get());
                        pOutput.accept(ModItems.SUIT_LEGGINGS.get());
                        pOutput.accept(ModItems.SUIT_BOOTS.get());
                        //katanas
                        pOutput.accept(ModItems.BASE_KATANA.get());
                        pOutput.accept(ModItems.JINCHU_MARU.get());
                        pOutput.accept(ModItems.TSUMETOGI.get());
                        pOutput.accept(ModItems.SCALPEL.get());
                        pOutput.accept(ModItems.COCKTAIL_STICK.get());
                        pOutput.accept(ModItems.BYAKKO.get());
                        pOutput.accept(ModItems.ERRATA.get());
                        pOutput.accept(ModItems.SATORI.get());
                        //one-handed bats
                        pOutput.accept(ModItems.DING_DING.get());
                        pOutput.accept(ModItems.MURPHY.get());
                        pOutput.accept(ModItems.BOOMER.get());
                        pOutput.accept(ModItems.GOLD_BAT.get());
                        //hammer
                        pOutput.accept(ModItems.SASQUATCH_HAMMER.get());
                        //non-throwable
                        pOutput.accept(ModItems.CLEAVER.get());
                        //throwable items
                        pOutput.accept(ModItems.NEHAN.get());
                        pOutput.accept(ModItems.FANG.get());
                        pOutput.accept(ModItems.BLUE_FANG.get());
                        pOutput.accept(ModItems.HEADHUNTER.get());
                    })).build());

    public static final RegistryObject<CreativeModeTab> CPW_CRAFTING_TAB = CREATIVE_MODE_TABS.register("cpw_crafting_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.B_BASE_KATANA.get()))
                    .title(Component.translatable("creativetab.cpw_crafting.tab"))
                    .displayItems(((pParameters, pOutput) -> {
                        //crafting items
                        pOutput.accept(ModItems.CARBON_STONE.get());
                        pOutput.accept(ModItems.FORGED_STONE.get());
                        pOutput.accept(ModItems.STONE_MESH.get());
                        pOutput.accept(ModItems.SPACE_STATION_MUSIC_DISK.get());
                        pOutput.accept(ModItems.TRAVELER_MUSIC_DISK.get());
                        //printer
                        pOutput.accept(ModBlocks.PRINTER_BLOCK.get());
                        //blueprints
                        pOutput.accept(ModItems.BLUEPRINT_TEMPLATE.get());
                        pOutput.accept(ModItems.B_STATION.get());
                        pOutput.accept(ModItems.B_TRAVELER.get());
                        pOutput.accept(ModItems.BULLET_PENDANT.get());
                        pOutput.accept(ModItems.AGENT_CARD.get());
                        pOutput.accept(ModItems.B_PUNK_JACKET.get());
                        pOutput.accept(ModItems.B_BASE_KATANA.get());
                        pOutput.accept(ModItems.B_JINCHU_MARU.get());
                        pOutput.accept(ModItems.B_SCALPEL.get());
                        pOutput.accept(ModItems.B_TSUMETOGI.get());
                        pOutput.accept(ModItems.B_COCKTAIL_STICK.get());
                        pOutput.accept(ModItems.B_BYAKKO.get());
                        pOutput.accept(ModItems.B_ERRATA.get());
                        pOutput.accept(ModItems.B_SATORI.get());
                        pOutput.accept(ModItems.B_DING_DING.get());
                        pOutput.accept(ModItems.B_MURPHY.get());
                        pOutput.accept(ModItems.B_BOOMER.get());
                        pOutput.accept(ModItems.B_GOLD_BAT.get());
                        pOutput.accept(ModItems.B_HAMMER.get());
                        pOutput.accept(ModItems.B_CLEAVER.get());
                        pOutput.accept(ModItems.B_NEHAN.get());
                        pOutput.accept(ModItems.B_BLUE_FANG.get());
                        pOutput.accept(ModItems.B_FANG.get());
                        pOutput.accept(ModItems.B_HEADHUNTER.get());
                    })).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
