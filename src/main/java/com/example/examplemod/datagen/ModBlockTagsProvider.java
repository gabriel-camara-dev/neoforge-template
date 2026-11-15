package com.example.examplemod.datagen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

/** Tags de blocos: ferramenta certa para minerar, nível de ferramenta e tags próprias. */
public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ExampleMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.EXAMPLE_BLOCK.getKey());
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.EXAMPLE_COUNTER_BLOCK.getKey());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.EXAMPLE_BLOCK.getKey());

        tag(ModTags.Blocks.EXAMPLE_DETECTABLES)
                .addTag(Tags.Blocks.ORES)
                .add(ModBlocks.EXAMPLE_BLOCK.getKey());
    }
}
