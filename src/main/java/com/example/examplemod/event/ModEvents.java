package com.example.examplemod.event;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.networking.ServerPayloadHandler;
import com.example.examplemod.networking.packet.ExamplePacketC2S;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

/**
 * Eventos comuns (rodam no cliente e no servidor). O @EventBusSubscriber registra
 * automaticamente todo método static com @SubscribeEvent, seja evento do jogo ou do mod.
 * <p>
 * Lugar certo para: atributos de entidades (EntityAttributeCreationEvent),
 * spawn placements, registro de pacotes, eventos de jogador/dano/etc.
 */
@EventBusSubscriber(modid = ExampleMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        event.getEntity().sendSystemMessage(Component.translatable("message.examplemod.welcome"));
    }

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1")
                .executesOn(HandlerThread.MAIN);

        registrar.playToServer(ExamplePacketC2S.TYPE, ExamplePacketC2S.STREAM_CODEC, ServerPayloadHandler::handleExamplePacket);
    }

    // Exemplo para entidades:
    // @SubscribeEvent
    // public static void registerAttributes(EntityAttributeCreationEvent event) {
    //     event.put(ModEntities.MY_ENTITY.get(), MyEntity.createAttributes().build());
    // }
}
