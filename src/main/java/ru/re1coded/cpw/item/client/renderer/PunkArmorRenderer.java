package ru.re1coded.cpw.item.client.renderer;

import ru.re1coded.cpw.item.armor.PunkArmorItem;
import ru.re1coded.cpw.item.client.model.PunkArmorModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class PunkArmorRenderer extends GeoArmorRenderer<PunkArmorItem> {

    public PunkArmorRenderer() {
        super(new PunkArmorModel());
    }
}
