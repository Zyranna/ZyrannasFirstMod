package net.zyranna.startermod.starteritem.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.zyranna.startermod.starterMod;
import net.zyranna.startermod.starteritem.StarterItems;
import net.zyranna.startermod.util.ModTags;

import java.util.List;

public class ModToolTiers{

    public static final Tier Bloodstone = TierSortingRegistry.registerTier(
                new ForgeTier(
                        5,
                        5000,
                        7f,
                        2f,
                        24,
                        ModTags.Blocks.NEEDS_BLOODSTONE_TOOL,
                        () -> Ingredient.of(StarterItems.BLOODSTONE.get())),
                new ResourceLocation(starterMod.MOD_ID,"bloodstone"),
               List.of(Tiers.NETHERITE), List.of(/*ModToolTiers.Zyrannite*/));

    public static final Tier Zyrannite = TierSortingRegistry.registerTier(
            new ForgeTier(
                    6,
                    6000,
                    9f,
                    3f,
                    34,
                    ModTags.Blocks.NEEDS_ZYRANNITE_TOOL,
                    () -> Ingredient.of(StarterItems.ZYRANNITE.get())),
            new ResourceLocation(starterMod.MOD_ID,"zyrannite"),
            List.of(ModToolTiers.Bloodstone), List.of());



}