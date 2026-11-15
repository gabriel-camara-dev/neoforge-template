package com.example.examplemod.sound;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Registry de sons. Para adicionar um som:
 * <ol>
 *   <li>Coloque o arquivo .ogg em assets/&lt;modid&gt;/sounds/meu_som.ogg</li>
 *   <li>Registre aqui (exemplo abaixo)</li>
 *   <li>Crie um ModSoundsProvider (extends SoundDefinitionsProvider) no datagen, ou escreva sounds.json à mão</li>
 * </ol>
 */
public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, ExampleMod.MOD_ID);

    // Exemplo (descomente quando tiver o .ogg; precisa importar Supplier e Identifier):
    // public static final Supplier<SoundEvent> MY_SOUND = SOUND_EVENTS.register("my_sound",
    //         () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(ExampleMod.MOD_ID, "my_sound")));

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
