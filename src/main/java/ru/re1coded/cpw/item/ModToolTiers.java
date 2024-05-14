package ru.re1coded.cpw.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;
import ru.re1coded.cpw.BladeMod;

import java.util.List;

//Relic tool tier (like the name of chip's project, will change... or not)
public class ModToolTiers {
    public static final Tier RELIC = TierSortingRegistry.registerTier(
            new ForgeTier(5, 10000, 2.5f, 4f, 25, Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ModItems.STONE_MESH.get())),
            new ResourceLocation(BladeMod.MOD_ID, "relic"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier RELIC_NBREACK = TierSortingRegistry.registerTier(
            new ForgeTier(5, -1, 2.5f, 6f, 25, Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ModItems.STONE_MESH.get())),
            new ResourceLocation(BladeMod.MOD_ID, "relic_unbreackble"), List.of(Tiers.NETHERITE), List.of());
}
