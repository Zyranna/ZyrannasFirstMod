package net.zyranna.startermod.starteritem;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab STARTER_TAB = new CreativeModeTab("startermodtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(StarterItems.ZYRANNITE.get());
        }
    };
}
