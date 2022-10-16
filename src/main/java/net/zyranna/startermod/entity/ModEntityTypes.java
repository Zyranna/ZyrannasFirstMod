package net.zyranna.startermod.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zyranna.startermod.entity.custom.SharkPuppy;
import net.zyranna.startermod.starterMod;

public class ModEntityTypes {

    public static  final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, starterMod.MOD_ID);


    public static final RegistryObject<EntityType<? extends SharkPuppy>> SHARKPUP =
            ENTITY_TYPES.register("sharkpup", () -> EntityType.Builder.of(SharkPuppy::new, MobCategory.WATER_CREATURE).sized(.5f,.5f).build(new ResourceLocation(starterMod.MOD_ID, "sharkpup").toString()));




    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);

    }
}
