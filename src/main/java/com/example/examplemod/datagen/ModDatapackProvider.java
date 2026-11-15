package com.example.examplemod.datagen;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

/**
 * Registries de datapack geradas pelo datagen.
 * <ul>
 *   <li>RELOADABLE_BUILDER: loot tables, receitas, advancements (recarregam com /reload)</li>
 *   <li>WORLD_BUILDER: worldgen (features, biome modifiers), damage types, pinturas, músicas...</li>
 * </ul>
 */
public class ModDatapackProvider {
    public static final RegistrySetBuilder WORLD_BUILDER = new RegistrySetBuilder();
    // Ex.: .add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap)
    //      .add(Registries.CONFIGURED_FEATURE / PLACED_FEATURE, ...)

    public static final RegistrySetBuilder RELOADABLE_BUILDER = new RegistrySetBuilder()
            .add(Registries.LOOT_TABLE, new LootTableProvider(Set.of(), List.of(
                    new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK))))
            .add(ModRecipeProvider.create());
}
