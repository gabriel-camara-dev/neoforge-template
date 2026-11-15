package com.example.examplemod.tags;

import com.example.examplemod.ExampleMod;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/** TagKeys próprias do mod. O conteúdo das tags é definido nos TagsProviders (datagen). */
public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> EXAMPLE_DETECTABLES = createTag("example_detectables");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(ExampleMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> EXAMPLE_ITEMS = createTag("example_items");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(ExampleMod.MOD_ID, name));
        }
    }
}
