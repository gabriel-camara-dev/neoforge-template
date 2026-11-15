package com.example.examplemod.effect;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Registry de efeitos de poção. Ícone em assets/&lt;modid&gt;/textures/mob_effect/&lt;nome&gt;.png */
public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, ExampleMod.MOD_ID);

    public static final Holder<MobEffect> EXAMPLE_EFFECT = MOB_EFFECTS.register("example_effect",
            () -> new ExampleEffect(MobEffectCategory.BENEFICIAL, 0x3fa9f5));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
