package net.zyranna.startermod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zyranna.startermod.block.custom.JumpyBlock;
import net.zyranna.startermod.starterMod;
import net.zyranna.startermod.starteritem.ModCreativeModeTab;
import net.zyranna.startermod.starteritem.StarterItems;

import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block>BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, starterMod.MOD_ID);
    // set up the deferred register for all blocks

    public static final RegistryObject<Block> BLOODSTONE_BLOCK = registerBlocks("bloodstone_block", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST)
            .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.STARTER_TAB);

    public static final RegistryObject<Block> BOODSTONE_ORE = registerBlocks("bloodstone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
            .strength(6f).requiresCorrectToolForDrops(),UniformInt.of(4, 8)), ModCreativeModeTab.STARTER_TAB);

    public static final RegistryObject<Block> ZYRANNITE_BLOCK = registerBlocks("zyrannite_block",() -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST)
            .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.STARTER_TAB);
        //set up the registration for the Zyrannite block, includes creativemode tab assignment and block properties

    public static final RegistryObject<Block> ZYRANNITE_ORE = registerBlocks("zyrannite_ore",() -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
            .strength(6f).requiresCorrectToolForDrops(), UniformInt.of(4, 8)), ModCreativeModeTab.STARTER_TAB);
        // set up the registration for the Zyrannite ore block, includes Creativemode tab assignment and block properties

    public static final RegistryObject<Block> Jumpy_Block = registerBlocks("jumpy_block", () -> new JumpyBlock(BlockBehaviour.Properties.of(Material.AMETHYST)
            .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.STARTER_TAB);


    private static <T extends Block>RegistryObject<T> registerBlocks(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab){
        return StarterItems.Items.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
