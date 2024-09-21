package com.foxy_571.omegapoo.block.custom;

import com.foxy_571.omegapoo.block.entity.FilterBlockEntity;
import com.foxy_571.omegapoo.block.entity.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FilterBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public static final MapCodec<FilterBlock> CODEC = simpleCodec(FilterBlock::new);

    public FilterBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected @NotNull MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return ModBlockEntities.FILTER.get().create(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof FilterBlockEntity filterBlockEntity) {
                ItemStack item = filterBlockEntity.getItem(0);
                if (stack.isEmpty()) {
                    if (item.isEmpty()) {
                        player.sendSystemMessage(Component.literal("No item in filter block!"));
                        return ItemInteractionResult.SUCCESS;
                    }

                    player.setItemInHand(hand, filterBlockEntity.getItem(0).split(1));
                }
                else if (item.isEmpty()) {
                    filterBlockEntity.setItem(0, player.isCreative() ? stack.copyWithCount(1) : stack.split(1));
                }

                return ItemInteractionResult.SUCCESS;
            }
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof FilterBlockEntity filterBlockEntity) {
                if (!level.isClientSide()) {
                    Containers.dropContents(level, pos, filterBlockEntity);
                }

                super.onRemove(state, level, pos, newState, isMoving);
                level.updateNeighbourForOutputSignal(pos, this);
            } else {
                super.onRemove(state, level, pos, newState, isMoving);
            }
        }
    }
}
