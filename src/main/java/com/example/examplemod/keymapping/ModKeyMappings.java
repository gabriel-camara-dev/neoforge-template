package com.example.examplemod.keymapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.common.util.Lazy;

/** Teclas configuráveis (só cliente). Registradas em ExampleModClient#registerKeyMappings. */
public class ModKeyMappings {
    public static final Lazy<KeyMapping> EXAMPLE_KEY = Lazy.of(() ->
            new KeyMapping("key.examplemod.example", InputConstants.KEY_K, KeyMapping.Category.MISC));
}
