package net.zyranna.startermod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.zyranna.startermod.entity.custom.SharkPuppy;
import net.zyranna.startermod.starterMod;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SharkPupRenderer extends GeoEntityRenderer<SharkPuppy> {
    public SharkPupRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SharkPupModel());
        this.shadowRadius=0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(SharkPuppy instance) {
        return new ResourceLocation(starterMod.MOD_ID, "textures/entity/sharkpuptexture.png");
    }

    @Override
    public RenderType getRenderType(SharkPuppy animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
