package net.zyranna.startermod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.zyranna.startermod.block.ModBlocks;
import net.zyranna.startermod.starteritem.StarterItems;
import net.zyranna.startermod.world.feature.ModConfiguredFeatures;
import net.zyranna.startermod.world.feature.ModPlacedFeatures;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(starterMod.MOD_ID)
public class starterMod
{
    public static final String MOD_ID = "startermod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public starterMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        StarterItems.register(modEventBus);
        // register the list of items from the StarterItems file

        ModBlocks.register(modEventBus);
        // register the list of Blocks from the ModBlocks file

        ModConfiguredFeatures.CONFIGURED_FEATURES.register(modEventBus);
        ModPlacedFeatures.PLACED_FEATURES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
