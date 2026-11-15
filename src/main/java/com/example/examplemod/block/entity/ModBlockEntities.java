package com.example.examplemod.block.entity;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.block.entity.custom.ExampleCounterBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/** Registry de Block Entities. Cada tipo aponta para a classe e para os blocos que a usam. */
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ExampleMod.MOD_ID);

    public static final Supplier<BlockEntityType<ExampleCounterBlockEntity>> EXAMPLE_COUNTER_BE =
            BLOCK_ENTITIES.register("example_counter_be", () -> new BlockEntityType<>(
                    ExampleCounterBlockEntity::new, ModBlocks.EXAMPLE_COUNTER_BLOCK.get()));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
