package ru.re1coded.cpw.item.client.model;

import net.minecraft.resources.ResourceLocation;
import ru.re1coded.cpw.BladeMod;
import ru.re1coded.cpw.item.armor.SuitArmorItem;
import software.bernie.geckolib.model.GeoModel;

public class SuitArmorModel extends GeoModel<SuitArmorItem> {

    @Override
    public ResourceLocation getModelResource(SuitArmorItem suitArmorItem) {
        return new ResourceLocation(BladeMod.MOD_ID, "geo/agent_suit.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SuitArmorItem suitArmorItem) {
        return new ResourceLocation(BladeMod.MOD_ID, "textures/armor/suit_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SuitArmorItem suitArmorItem) {
        //return new ResourceLocation(MainMod.MOD_ID, "animation/model.animation.json");
        return null;
    }
}
