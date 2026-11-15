package com.example.examplemod;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Configuração do mod (ModConfigSpec). Registrada em {@link ExampleMod} como COMMON.
 * Cada valor vira uma linha em config/examplemod-common.toml e aparece na tela de config.
 */
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue LOG_ON_SETUP = BUILDER
            .comment("Se deve escrever uma mensagem no log durante o common setup")
            .define("logOnSetup", true);

    public static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("Um número de exemplo")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    static final ModConfigSpec SPEC = BUILDER.build();
}
