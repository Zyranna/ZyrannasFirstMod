package net.zyranna.startermod.starteritem;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zyranna.startermod.starteritem.custom.ModToolTiers;
import net.zyranna.startermod.starterMod;
import net.zyranna.startermod.starteritem.custom.PoweredZyrannite;

public class StarterItems {
    public static final DeferredRegister<Item> Items =
            DeferredRegister.create(ForgeRegistries.ITEMS, starterMod.MOD_ID );
    //set up the deferred register for all the items added


    // Register the Resource items
    public static final RegistryObject<Item> ZYRANNITE = Items.register("zyrannite", () ->
            new Item(props()));
    public static final RegistryObject<Item> BLOODSTONE = Items.register("bloodstone", () ->
            new Item(props()));



    // Register the *SPECIAL* items
    public static final RegistryObject<Item> Powered_Zyrannite = Items.register("powered_zyrannite", () ->
            new PoweredZyrannite(props().stacksTo(1)));



    // Register the Zyrannite Tool Items
    public static final RegistryObject<SwordItem> Zyrannite_Sword = Items.register("zyrannite_sword", () ->
            new SwordItem(ModToolTiers.Zyrannite,8,.5f,props()));
    public static final RegistryObject<ShovelItem> Zyrannite_Shovel = Items.register("zyrannite_shovel", () ->
            new ShovelItem(ModToolTiers.Zyrannite,1f,1f,props()));
    public static final RegistryObject<HoeItem> Zyrannite_Hoe = Items.register("zyrannite_hoe", () ->
            new HoeItem(ModToolTiers.Zyrannite,0,1f,props()));
    public static final RegistryObject<PickaxeItem> Zyrannite_Pickaxe = Items.register("zyrannite_pickaxe", () ->
            new PickaxeItem(ModToolTiers.Zyrannite,1,1f,props()));
    public static final RegistryObject<AxeItem> Zyrannite_Axe = Items.register("zyrannite_axe", () ->
            new AxeItem(ModToolTiers.Zyrannite,5f,1f,props()));


    // Register the Bloodstone Tool Items
    public static final RegistryObject<AxeItem> Bloodstone_Axe = Items.register("bloodstone_axe", () ->
            new AxeItem(ModToolTiers.Bloodstone,5f,1f,props()));
    public static final RegistryObject<PickaxeItem> Bloodstone_Pickaxe = Items.register("bloodstone_pickaxe", () ->
            new PickaxeItem(ModToolTiers.Bloodstone,1,1f,props()));
    public static final RegistryObject<HoeItem> Bloodstone_Hoe = Items.register("bloodstone_hoe", () ->
            new HoeItem(ModToolTiers.Bloodstone,0,1f,props()));
    public static final RegistryObject<ShovelItem> Bloodstone_Shovel = Items.register("bloodstone_shovel", () ->
            new ShovelItem(ModToolTiers.Bloodstone,1f,1f,props()));
    public static final RegistryObject<SwordItem> Bloodstone_Sword = Items.register("bloodstone_sword", () ->
            new SwordItem(ModToolTiers.Bloodstone,8,.5f,props()));






    private static Item.Properties props(){
        //  THIS FUCKING AMAZING IDEA TO MAKE A METHOD FOR ASSIGNING THE CREATIVE MODE TAB
        return new Item.Properties().tab(ModCreativeModeTab.STARTER_TAB);
    }



    // Register all above items
    public static void register(IEventBus eventBus){
        Items.register(eventBus);
    }
}
