package com.example.examplemod.entity;

import com.example.examplemod.ExampleMod;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Registry de entidades. Uma entidade completa precisa de várias peças:
 * <ul>
 *   <li>entity/custom/MyEntity.java ......... a lógica (extends Animal, Monster, PathfinderMob...)</li>
 *   <li>entity/client/MyEntityModel.java .... o modelo (exportado do Blockbench)</li>
 *   <li>entity/client/MyEntityRenderer.java . o renderer + RenderState</li>
 *   <li>entity/client/ModModelLayerLocations  as ModelLayerLocations</li>
 *   <li>ModEvents ........................... EntityAttributeCreationEvent (atributos)</li>
 *   <li>ExampleModClient .................... registrar renderer e layer definition</li>
 * </ul>
 * Exemplo de registro (precisa importar ResourceKey, Registries, Identifier, MobCategory e Supplier):
 * <pre>{@code
 * public static final ResourceKey<EntityType<?>> MY_ENTITY_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
 *         Identifier.fromNamespaceAndPath(ExampleMod.MOD_ID, "my_entity"));
 *
 * public static final Supplier<EntityType<MyEntity>> MY_ENTITY = ENTITY_TYPES.register("my_entity",
 *         () -> EntityType.Builder.of(MyEntity::new, MobCategory.CREATURE).sized(1f, 1f).build(MY_ENTITY_KEY));
 * }</pre>
 * Veja as entidades Dodo/Warturtle no repo do Kaupenjoe como referência completa.
 */
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.createEntities(ExampleMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
