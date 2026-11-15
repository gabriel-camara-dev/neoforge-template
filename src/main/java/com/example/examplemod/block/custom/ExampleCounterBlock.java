package com.example.examplemod.block.custom;

import com.example.examplemod.block.entity.custom.ExampleCounterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

/**
 * Exemplo de bloco com Block Entity: cada clique com a mão vazia incrementa
 * um contador salvo no {@link ExampleCounterBlockEntity}.
 */
public class ExampleCounterBlock extends BaseEntityBlock {
    public ExampleCounterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new ExampleCounterBlockEntity(worldPosition, blockState);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hitResult) {
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof ExampleCounterBlockEntity counter) {
            int value = counter.increment();
            player.sendSystemMessage(Component.translatable("block.examplemod.example_counter_block.count", value));
        }
        return InteractionResult.SUCCESS;
    }
}
