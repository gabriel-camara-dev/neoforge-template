package com.example.examplemod.datagen;

import com.example.examplemod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

/**
 * Loot tables (o que cada bloco dropa). Todo bloco do mod precisa de uma entrada aqui,
 * senão o datagen acusa erro. Exemplos úteis: dropSelf, createOreDrop, createSlabItemTable, createDoorTable.
 */
public class ModBlockLootTableProvider extends BlockLootSubProvider {
    public ModBlockLootTableProvider(LootTableSubProvider.Context context) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), context);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.EXAMPLE_BLOCK.get());
        dropSelf(ModBlocks.EXAMPLE_COUNTER_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
