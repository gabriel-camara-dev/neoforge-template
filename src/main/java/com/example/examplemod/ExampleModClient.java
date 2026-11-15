package com.example.examplemod;

import com.example.examplemod.keymapping.ModKeyMappings;
import com.example.examplemod.networking.packet.ExamplePacketC2S;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

/**
 * Ponto de entrada SÓ DO CLIENTE. Esta classe não é carregada em servidor dedicado,
 * então é seguro referenciar classes de cliente (Minecraft, renderers, telas...) aqui.
 * <p>
 * Use para: renderers de entidade/block entity, model layers, telas (menus), key mappings,
 * partículas e qualquer evento de cliente.
 */
@Mod(value = ExampleMod.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = ExampleMod.MOD_ID, value = Dist.CLIENT)
public class ExampleModClient {
    public ExampleModClient(ModContainer container) {
        // Cria automaticamente uma tela de config em Mods > Example Mod > Config.
        // Lembre de traduzir as chaves da config no arquivo de lang.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Ex.: EntityRenderers.register(ModEntities.MY_ENTITY.get(), MyEntityRenderer::new);
        ExampleMod.LOGGER.info("[{}] Client setup!", ExampleMod.MOD_ID);
    }

    // Outros eventos de cliente úteis (descomente os imports quando usar):
    // EntityRenderersEvent.RegisterLayerDefinitions -> model layers de entidades
    // EntityRenderersEvent.RegisterRenderers        -> renderers de block entities
    // RegisterMenuScreensEvent                      -> telas de menus/GUIs

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(ModKeyMappings.EXAMPLE_KEY.get());
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        while (ModKeyMappings.EXAMPLE_KEY.get().consumeClick()) {
            // Estamos no CLIENTE: mostramos algo localmente e mandamos um pacote pro SERVIDOR.
            Minecraft.getInstance().player.sendSystemMessage(Component.translatable("message.examplemod.key_pressed"));
            ClientPacketDistributor.sendToServer(new ExamplePacketC2S("hello", 42));
        }
    }
}
