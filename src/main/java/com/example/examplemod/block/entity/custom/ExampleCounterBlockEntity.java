package com.example.examplemod.block.entity.custom;

import com.example.examplemod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

/** Block Entity mínima: guarda um inteiro e persiste no save do mundo. */
public class ExampleCounterBlockEntity extends BlockEntity {
    private int count = 0;

    public ExampleCounterBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.EXAMPLE_COUNTER_BE.get(), worldPosition, blockState);
    }

    public int increment() {
        count++;
        setChanged(); // avisa o jogo que precisa salvar este chunk
        return count;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("count", count);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        count = input.getIntOr("count", 0);
    }
}
