package com.foxy_571.omegapoo.item.custom;

import com.foxy_571.omegapoo.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class PlungerItem extends Item {
    public PlungerItem(Properties properties) {
        super(properties);
    }

    public static Tool createToolProperties() {
        return new Tool(List.of(Tool.Rule.overrideSpeed(List.of(ModBlocks.POOP_BLOCK.get(), ModBlocks.POOP_CARPET.get()), 10.0F)), 1.0F, 1);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (!level.isClientSide && !state.is(BlockTags.FIRE)) {
            stack.hurtAndBreak(1, entityLiving, EquipmentSlot.MAINHAND);
        }

        return state.is(ModBlocks.POOP_BLOCK) || state.is(ModBlocks.POOP_CARPET);
    }

//    @Override
//    public float getDestroySpeed(ItemStack stack, BlockState state) {
//        return state.is(ModBlocks.POOP_BLOCK.get()) || state.is(ModBlocks.POOP_CARPET.get()) ? 10.0F : super.getDestroySpeed(stack, state);
//    }
}