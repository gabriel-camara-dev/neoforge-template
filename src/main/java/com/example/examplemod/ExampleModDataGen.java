package com.example.examplemod;

import com.example.examplemod.datagen.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

/**
 * Data Generation. Rode a run config "Data" (ou ./gradlew runData) para gerar
 * models, blockstates, tags, loot tables e receitas em src/generated/resources.
 * <p>
 * Cada provider fica no pacote {@code datagen}. Para adicionar um novo:
 * crie a classe e registre aqui com {@code generator.addProvider(true, ...)}.
 */
@EventBusSubscriber(modid = ExampleMod.MOD_ID)
public class ExampleModDataGen {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        var lookupProvider = event.getReloadableLookupProvider();

        // Registries de datapack: loot tables e receitas (ver ModDatapackProvider)
        event.createReloadableRegistryObjects(ModDatapackProvider.RELOADABLE_BUILDER);
        // Para worldgen, damage types, pinturas etc. use também:
        // event.createWorldRegistryObjects(ModDatapackProvider.WORLD_BUILDER);

        generator.addProvider(true, new ModModelProvider(packOutput));
        generator.addProvider(true, new ModBlockTagsProvider(packOutput, lookupProvider));
        generator.addProvider(true, new ModItemTagsProvider(packOutput, lookupProvider));
    }
}
