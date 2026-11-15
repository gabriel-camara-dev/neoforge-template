package com.example.examplemod.networking.packet;

import com.example.examplemod.ExampleMod;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

/**
 * Pacote Cliente -> Servidor (C2S). O StreamCodec diz como transformar o record em bytes
 * e de volta. Registrado em ModEvents#registerPayloads.
 */
public record ExamplePacketC2S(String message, int value) implements CustomPacketPayload {
    public static final Type<ExamplePacketC2S> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(ExampleMod.MOD_ID, "example_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ExamplePacketC2S> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, ExamplePacketC2S::message,
            ByteBufCodecs.VAR_INT, ExamplePacketC2S::value,
            ExamplePacketC2S::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
