package net.zyranna.startermod.world.feature;

import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zyranna.startermod.starterMod;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, starterMod.MOD_ID);

    public static final RegistryObject<PlacedFeature> BLOODSTONE_ORE_PLACED = PLACED_FEATURES.register("bloodstone_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.BLOODSTONE_ORE.getHolder().get(),
                    commonOrePlacement(40,
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-60),VerticalAnchor.absolute(160)))));

    public static final RegistryObject<PlacedFeature> ZYRANNITE_ORE_PLACED = PLACED_FEATURES.register("zyrannite_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ZYRANNITE_ORE.getHolder().get(),
                    commonOrePlacement(40,
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-60),VerticalAnchor.absolute(160)))));




    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }






    public static void register(IEventBus eventBus){
        PLACED_FEATURES.register(eventBus);
    }

}
