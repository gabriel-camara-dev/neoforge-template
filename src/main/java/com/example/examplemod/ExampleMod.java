package com.example.examplemod;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.block.entity.ModBlockEntities;
import com.example.examplemod.component.ModDataComponents;
import com.example.examplemod.creativemodetab.ModCreativeModeTabs;
import com.example.examplemod.effect.ModEffects;
import com.example.examplemod.entity.ModEntities;
import com.example.examplemod.item.ModItems;
import com.example.examplemod.sound.ModSounds;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

/**
 * Classe principal do mod (lado comum: cliente + servidor).
 * <p>
 * Responsabilidade: ligar cada registry ({@code Mod*.register(modEventBus)}) ao event bus do mod.
 * Cada "camada" do mod vive no seu próprio pacote, com uma classe {@code ModXxx} que guarda o
 * {@code DeferredRegister} e as entradas registradas.
 * <p>
 * O valor de {@link #MOD_ID} precisa ser igual ao {@code mod_id} do gradle.properties.
 */
@Mod(ExampleMod.MOD_ID)
public class ExampleMod {
    public static final String MOD_ID = "examplemod";
    public static final Logger LOGGER = LogUtils.getLogger();

    // O construtor é o primeiro código do mod a rodar.
    // O FML injeta automaticamente IEventBus e ModContainer.
    public ExampleMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        // ===== Registries (ordem não importa, mas manter agrupado ajuda) =====
        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        ModDataComponents.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEffects.register(modEventBus);
        ModEntities.register(modEventBus);

        // Config COMMON (gera config/examplemod-common.toml)
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        if (Config.LOG_ON_SETUP.get()) {
            LOGGER.info("[{}] Common setup! Número mágico: {}", MOD_ID, Config.MAGIC_NUMBER.get());
        }
    }

    // Adiciona itens do mod às abas vanilla do modo criativo.
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.EXAMPLE_ITEM);
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.EXAMPLE_BLOCK);
        }
    }
}
