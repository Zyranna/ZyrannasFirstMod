package net.zyranna.startermod.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zyranna.startermod.entity.ModEntityTypes;
import net.zyranna.startermod.entity.custom.SharkPuppy;
import net.zyranna.startermod.starterMod;



public class ModEvents {
    @Mod.EventBusSubscriber(modid = starterMod.MOD_ID)
    public static class ForgeEvents{












    }

    @Mod.EventBusSubscriber(modid = starterMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents{
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event){
            event.put(ModEntityTypes.SHARKPUP.get(), SharkPuppy.setAttributes());
        }

    }



}
