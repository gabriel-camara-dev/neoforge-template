package com.example.examplemod.datagen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.item.ModItems;
import com.example.examplemod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

/** Tags de itens. */
public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ExampleMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModTags.Items.EXAMPLE_ITEMS)
                .add(ModItems.EXAMPLE_ITEM.getKey())
                .add(ModItems.EXAMPLE_TOOL.getKey());
    }
}
