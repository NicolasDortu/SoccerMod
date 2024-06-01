package net.nico.soccermod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModTabs {
    public static final CreativeModeTab SOCCER_TAB = new CreativeModeTab("soccer_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SOCCERBALLITEM.get());
        }

        @Override
        public Component getDisplayName() {
            return Component.translatable("itemGroup.soccer_tab");
        }
    };
}
