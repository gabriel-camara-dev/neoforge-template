package com.example.examplemod.networking;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.networking.packet.ExamplePacketC2S;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/** Trata pacotes que chegam DO cliente. Este código roda NO SERVIDOR. */
public class ServerPayloadHandler {
    public static void handleExamplePacket(ExamplePacketC2S packet, IPayloadContext context) {
        ExampleMod.LOGGER.info("[{}] {} enviou '{}' ({})", ExampleMod.MOD_ID,
                context.player().getName().getString(), packet.message(), packet.value());
    }
}
