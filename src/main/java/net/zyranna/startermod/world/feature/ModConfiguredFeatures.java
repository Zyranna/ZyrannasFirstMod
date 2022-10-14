package net.zyranna.startermod.world.feature;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zyranna.startermod.block.ModBlocks;
import net.zyranna.startermod.starterMod;

import java.util.List;
import java.util.function.Supplier;


public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, starterMod.MOD_ID);

    private static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_BLOODSTONE_ORE = Suppliers.memoize(() -> List.of(
         OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.BOODSTONE_ORE.get().defaultBlockState())));
    // placement registry for nether ores


    private static final Supplier<List<OreConfiguration.TargetBlockState>> END_ZYRANNITE_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), ModBlocks.ZYRANNITE_ORE.get().defaultBlockState())));
    //placement registry for End ores



    public static final RegistryObject<ConfiguredFeature<?,?>> BLOODSTONE_ORE= CONFIGURED_FEATURES.register("bloodstone_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(END_ZYRANNITE_ORE.get(),8)));
    public static final RegistryObject<ConfiguredFeature<?,?>>ZYRANNITE_ORE = CONFIGURED_FEATURES.register("zyrannite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_BLOODSTONE_ORE.get(), 8)));


    public static void register(IEventBus eventBus){
        CONFIGURED_FEATURES.register(eventBus);
    }

}
