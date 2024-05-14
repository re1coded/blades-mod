package ru.re1coded.cpw.item.client.model;

import net.minecraft.resources.ResourceLocation;
import ru.re1coded.cpw.BladeMod;
import ru.re1coded.cpw.item.armor.PunkArmorItem;
import software.bernie.geckolib.model.GeoModel;

public class PunkArmorModel extends GeoModel<PunkArmorItem> {
    @Override
    public ResourceLocation getModelResource(PunkArmorItem punkArmorItem) {
        return new ResourceLocation(BladeMod.MOD_ID, "geo/punk_jacket.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PunkArmorItem punkArmorItem) {
        return new ResourceLocation(BladeMod.MOD_ID, "textures/armor/punk_jacket_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PunkArmorItem punkArmorItem) {
        //return new ResourceLocation(MainMod.MOD_ID, "animation/model.animation.json");
        return null;
    }
}
