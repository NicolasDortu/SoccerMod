package assets.soccermod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.nico.soccermod.SoccerMod;

public class ModTags {
    public static class Blocks {

        @SuppressWarnings({ "unused", "null" })
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(SoccerMod.MOD_ID, name));
        }
    }

    @SuppressWarnings({ "null", "unused" })
    private static TagKey<Item> tag(String name) {
        return ItemTags.create(new ResourceLocation(SoccerMod.MOD_ID, name));
    }
}
