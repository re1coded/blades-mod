package ru.re1coded.cpw.item.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;


import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;
import ru.re1coded.cpw.entity.projectile.ThrowingWeaponEntity;

public class ThrowingWeaponRenderer<T extends ThrowingWeaponEntity> extends EntityRenderer<T> {
    private final ItemRenderer itemRenderer;

    public ThrowingWeaponRenderer(EntityRendererProvider.Context rendererProvider) {
        super(rendererProvider);
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        Vector3f nextRotateAxis = new Vector3f(1.5f, 1.5f, 0.0f);
        doRenderTransformations(pEntity, pPartialTick, pPoseStack);
        nextRotateAxis.normalize();
        pPoseStack.mulPose(Axis.YP.rotationDegrees(0.0F)); //please don't touch, I'll think about it later
        pPoseStack.mulPose(Axis.ZN.rotationDegrees(145.0F));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(90.0F));

        pPoseStack.translate(0.4d, 0.25d, 0.0d);
        ItemStack weapon = pEntity.getWeaponItem();
        if(!weapon.isEmpty())
        {
            BakedModel bakedModel = this.itemRenderer.getModel(weapon, pEntity.level(), (LivingEntity)null, pEntity.getId());
            this.itemRenderer.render(weapon, ItemDisplayContext.GROUND, false, pPoseStack, pBuffer, pPackedLight, OverlayTexture.NO_OVERLAY, bakedModel);
        }
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity)
    {
        return TextureAtlas.LOCATION_BLOCKS;
    }
    protected void doRenderTransformations(T entity, float partialTicks, PoseStack matrixStack)
    {
        matrixStack.scale(2.0f, 2.0f, 2.0f);
        matrixStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0f));
        matrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()) - 45.0f));

    }

}
