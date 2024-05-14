package ru.re1coded.cpw.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.re1coded.cpw.BladeMod;
//import ru.re1coded.cpw.item.armor.PunkArmorItem;
//import ru.re1coded.cpw.item.armor.SuitArmorItem;
import ru.re1coded.cpw.item.armor.PunkArmorItem;
import ru.re1coded.cpw.item.armor.SuitArmorItem;
import ru.re1coded.cpw.item.weapon.*;
import ru.re1coded.cpw.item.weapon.knives.BlueFangItem;
import ru.re1coded.cpw.item.weapon.knives.FangItem;
import ru.re1coded.cpw.item.weapon.knives.HeadhunterItem;
import ru.re1coded.cpw.item.weapon.knives.ThrowingKnifeItem;
import ru.re1coded.cpw.sound.ModSounds;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BladeMod.MOD_ID);

    //crafting items
    public static final RegistryObject<Item> CARBON_STONE = ITEMS.register("carbon_stone", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FORGED_STONE = ITEMS.register("forged_stone", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STONE_MESH = ITEMS.register("stone_mesh", () -> new Item(new Item.Properties()));

    //music disks
    public static final RegistryObject<Item> SPACE_STATION_MUSIC_DISK = ITEMS.register("space_station_music_disk",
            () -> new RecordItem(6, ModSounds.SPACE_STATION, new Item.Properties().stacksTo(1), 3600));
    public static final RegistryObject<Item> TRAVELER_MUSIC_DISK = ITEMS.register("traveler_music_disk",
            () -> new RecordItem(6, ModSounds.TRAVELER, new Item.Properties().stacksTo(1), 3500));
    //blueprints
    public static final RegistryObject<Item> BLUEPRINT_TEMPLATE = ITEMS.register("blueprint_template", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> B_STATION = ITEMS.register("bl_space_station", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_TRAVELER = ITEMS.register("bl_traveler", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_BASE_KATANA = ITEMS.register("bl_base_katana", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_JINCHU_MARU = ITEMS.register("bl_jinchu_maru", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_SCALPEL = ITEMS.register("bl_scalpel", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_TSUMETOGI = ITEMS.register("bl_tsumetogi", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_COCKTAIL_STICK = ITEMS.register("bl_cocktail_stick", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_BYAKKO = ITEMS.register("bl_byakko", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_ERRATA = ITEMS.register("bl_errata", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_SATORI = ITEMS.register("bl_satori", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_DING_DING = ITEMS.register("bl_ding", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_MURPHY = ITEMS.register("bl_murphy", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_BOOMER = ITEMS.register("bl_boomer", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_GOLD_BAT = ITEMS.register("bl_gold_bat", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_HAMMER = ITEMS.register("bl_sasquatch", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_CLEAVER = ITEMS.register("bl_cleaver", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_NEHAN = ITEMS.register("bl_nehan", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_BLUE_FANG = ITEMS.register("bl_blue_fang", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_FANG = ITEMS.register("bl_fang", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_HEADHUNTER = ITEMS.register("bl_headhunter", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> AGENT_CARD = ITEMS.register("agent_card", () -> new CardItem(new Item.Properties()));
    public static final RegistryObject<Item> BULLET_PENDANT = ITEMS.register("bullet_pendant", () -> new BlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> B_PUNK_JACKET = ITEMS.register("bl_punk_jacket", () -> new BlueprintItem(new Item.Properties()));

    //Katanas
    public static final RegistryObject<Item> BASE_KATANA = ITEMS.register("base_katana",
            () -> new SwordItem(Tiers.IRON, 4, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> JINCHU_MARU = ITEMS.register("jinchu_maru",
            () -> new JinchuMaruItem(ModToolTiers.RELIC, 5, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> SCALPEL = ITEMS.register("scalpel",
            () -> new ScalpelItem(ModToolTiers.RELIC, 5, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> TSUMETOGI = ITEMS.register("tsumetogi",
            () -> new TsumetogiItem(ModToolTiers.RELIC, 5, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> COCKTAIL_STICK = ITEMS.register("cocktail_stick",
            () -> new SwordItem(ModToolTiers.RELIC, 5, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> BYAKKO = ITEMS.register("byakko",
            () -> new SwordItem(ModToolTiers.RELIC, 5, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> ERRATA = ITEMS.register("errata",
            () -> new ErrataItem(ModToolTiers.RELIC, 5, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> SATORI = ITEMS.register("satori",
            () -> new SwordItem(ModToolTiers.RELIC, 5, -2.4f, new Item.Properties()));

    //one-handed bat
    public static final RegistryObject<Item> DING_DING = ITEMS.register("ding",
            () -> new DingItem(ModToolTiers.RELIC, 4, -2.0f, new Item.Properties()));
    public static final RegistryObject<Item> MURPHY = ITEMS.register("murphy",
            () -> new MurphyItem(ModToolTiers.RELIC, 4, -2.0f, new Item.Properties()));
    public static final RegistryObject<Item> BOOMER = ITEMS.register("boomer",
            () -> new BoomerItem(ModToolTiers.RELIC, 4, -2.5f, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_BAT = ITEMS.register("gold_bat",
            () -> new GoldBatItem(ModToolTiers.RELIC, 4, -2.5f, new Item.Properties()));

    //hammer
    public static final RegistryObject<Item> SASQUATCH_HAMMER = ITEMS.register("sasquatch",
            () -> new HammerItem(ModToolTiers.RELIC, 4, -3.0f, new Item.Properties()));

    //non-throwable
    public static final RegistryObject<Item> CLEAVER = ITEMS.register("cleaver",
            () -> new CleaverItem(ModToolTiers.RELIC, 4, -2.0f, new Item.Properties()));

    //throwable knives
    public static final RegistryObject<Item> NEHAN = ITEMS.register("nehan",
           () -> new ThrowingKnifeItem(ModToolTiers.RELIC_NBREACK, 8, -2.0f, new Item.Properties()));
    public static final RegistryObject<Item> BLUE_FANG = ITEMS.register("blue_fang",
            () -> new BlueFangItem(ModToolTiers.RELIC_NBREACK, 8, -2.0f, new Item.Properties()));
    public static final RegistryObject<Item> FANG = ITEMS.register("fang",
            () -> new FangItem(ModToolTiers.RELIC_NBREACK, 8, -2.0f, new Item.Properties()));
    public static final RegistryObject<Item> HEADHUNTER = ITEMS.register("headhunter",
            () -> new HeadhunterItem(ModToolTiers.RELIC_NBREACK, 8, -2.0f, new Item.Properties()));

    //armor

    public static final RegistryObject<Item> PUNK_JACKET = ITEMS.register("punk_jacket",
            () -> new PunkArmorItem(CustomArmorMaterials.SUIT, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> SUIT_HELMET = ITEMS.register("suit_helmet",
            () -> new SuitArmorItem(CustomArmorMaterials.SUIT, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> SUIT_CHESTPLATE = ITEMS.register("suit_chestplate",
            () -> new SuitArmorItem(CustomArmorMaterials.SUIT, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> SUIT_LEGGINGS = ITEMS.register("suit_leggings",
            () -> new SuitArmorItem(CustomArmorMaterials.SUIT, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> SUIT_BOOTS = ITEMS.register("suit_boots",
            () -> new SuitArmorItem(CustomArmorMaterials.SUIT, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}