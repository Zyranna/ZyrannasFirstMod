package net.zyranna.startermod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.zyranna.startermod.entity.custom.SharkPuppy;
import net.zyranna.startermod.starterMod;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SharkPupModel extends AnimatedGeoModel<SharkPuppy> {
    @Override
    public ResourceLocation getModelResource(SharkPuppy object) {
        return new ResourceLocation(starterMod.MOD_ID,"geo/shark_pup.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SharkPuppy object) {
        return new ResourceLocation(starterMod.MOD_ID,"textures/entity/sharkpuptexture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SharkPuppy animatable) {
        return new ResourceLocation(starterMod.MOD_ID,"animations/model.animation.json");
    }
}
