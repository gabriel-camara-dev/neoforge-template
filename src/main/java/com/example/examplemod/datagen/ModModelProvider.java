package com.example.examplemod.datagen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

/**
 * Gera blockstates, models de bloco e models/client items.
 * ATENÇÃO: o datagen falha se algum bloco/item do mod ficar sem model aqui.
 */
public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, ExampleMod.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        /* ITEMS */
        itemModels.generateFlatItem(ModItems.EXAMPLE_ITEM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.EXAMPLE_TOOL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        /* BLOCKS (createTrivialCube também gera o model do BlockItem) */
        blockModels.createTrivialCube(ModBlocks.EXAMPLE_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.EXAMPLE_COUNTER_BLOCK.get());
    }
}
