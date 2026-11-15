package com.example.examplemod.item.custom;

import com.example.examplemod.component.ModDataComponents;
import com.example.examplemod.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;

/**
 * Exemplo de item customizado: ao clicar num bloco, verifica se ele está na tag
 * {@link ModTags.Blocks#EXAMPLE_DETECTABLES}, gasta durabilidade e guarda a posição
 * clicada num Data Component ({@link ModDataComponents#LAST_POSITION}).
 */
public class ExampleToolItem extends Item {
    public ExampleToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();

        if (!level.isClientSide() && player != null) {
            // Estamos no SERVIDOR
            BlockState state = level.getBlockState(pos);
            boolean matches = state.is(ModTags.Blocks.EXAMPLE_DETECTABLES);

            player.sendSystemMessage(Component.translatable(matches
                    ? "item.examplemod.example_tool.match"
                    : "item.examplemod.example_tool.no_match", state.getBlock().getName()));

            context.getItemInHand().set(ModDataComponents.LAST_POSITION, pos);
            context.getItemInHand().hurtAndBreak(1, player, context.getHand());
            level.playSound(null, pos, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1f, 1f);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display,
                                Consumer<Component> builder, TooltipFlag tooltipFlag) {
        builder.accept(Component.translatable("tooltip.examplemod.example_tool"));

        BlockPos last = itemStack.get(ModDataComponents.LAST_POSITION);
        if (last != null) {
            builder.accept(Component.translatable("tooltip.examplemod.example_tool.last_position",
                    last.getX(), last.getY(), last.getZ()));
        }

        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
