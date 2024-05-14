package ru.re1coded.cpw.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import ru.re1coded.cpw.BladeMod;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput packOutput) {
        super(packOutput, BladeMod.MOD_ID);
    }

    @Override
    protected void start() {
    }
}
