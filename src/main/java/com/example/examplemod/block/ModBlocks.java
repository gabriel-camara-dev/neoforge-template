package com.example.examplemod.block;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.custom.ExampleCounterBlock;
import com.example.examplemod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

/**
 * Registry de blocos. {@link #registerBlock} registra o bloco E o BlockItem dele de uma vez.
 * Lembre de: textura em assets/&lt;modid&gt;/textures/block, blockstate/model no ModModelProvider,
 * loot table no ModBlockLootTableProvider, ferramenta certa no ModBlockTagsProvider e nome no lang.
 */
public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ExampleMod.MOD_ID);

    public static final DeferredBlock<Block> EXAMPLE_BLOCK = registerBlock("example_block",
            properties -> new Block(properties.strength(3f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));

    // Bloco com Block Entity (guarda um contador).
    public static final DeferredBlock<Block> EXAMPLE_COUNTER_BLOCK = registerBlock("example_counter_block",
            properties -> new ExampleCounterBlock(properties.strength(2f).sound(SoundType.WOOD)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
