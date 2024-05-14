package ru.re1coded.cpw.item.client.renderer;

import ru.re1coded.cpw.item.armor.SuitArmorItem;
import ru.re1coded.cpw.item.client.model.SuitArmorModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class SuitArmorRenderer extends GeoArmorRenderer<SuitArmorItem> {
    public SuitArmorRenderer() {
        super(new SuitArmorModel());
    }
}
