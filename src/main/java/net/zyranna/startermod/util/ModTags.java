package net.zyranna.startermod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.zyranna.startermod.starterMod;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_ZYRANNITE_TOOL
                = tag("needs_zyrannite_tool");

            private static TagKey<Block> tag (String name){
                return BlockTags.create(new ResourceLocation(starterMod.MOD_ID,name));
            }
            private static TagKey<Block> forgeTag(String name){
                return BlockTags.create((new ResourceLocation("forge", name)));
            }


        public static final TagKey<Block> NEEDS_BLOODSTONE_TOOL
                = tag("needs_bloodstone_tool");

        private static TagKey<Block> tag2 (String name){
            return BlockTags.create(new ResourceLocation(starterMod.MOD_ID,name));
        }
        private static TagKey<Block> forgeTag2(String name){
            return BlockTags.create((new ResourceLocation("forge", name)));
        }


    }
}
