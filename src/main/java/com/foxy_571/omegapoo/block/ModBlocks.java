package com.foxy_571.omegapoo.block;

import com.foxy_571.omegapoo.OmegaPoo;
import com.foxy_571.omegapoo.block.custom.FilterBlock;
import com.foxy_571.omegapoo.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(OmegaPoo.MOD_ID);

    public static final DeferredBlock<Block> POOP_BLOCK = registerBlock("poop_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.HONEY_BLOCK).destroyTime(0.5F)));
    public static final DeferredBlock<CarpetBlock> POOP_CARPET = registerBlock("poop_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_CARPET).sound(SoundType.HONEY_BLOCK)));
    public static final DeferredBlock<Block> HARDENED_POOP = registerBlock("hardened_poop", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<StairBlock> HARDENED_POOP_STAIRS = registerBlock("hardened_poop_stairs", () -> new StairBlock(ModBlocks.HARDENED_POOP.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS)));
    public static final DeferredBlock<SlabBlock> HARDENED_POOP_SLAB = registerBlock("hardened_poop_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
    public static final DeferredBlock<Block> HARDENED_POOP_BRICKS = registerBlock("hardened_poop_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final DeferredBlock<Block> CHISELED_HARDENED_POOP_BRICKS = registerBlock("chiseled_hardened_poop_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS)));
    public static final DeferredBlock<StairBlock> HARDENED_POOP_BRICK_STAIRS = registerBlock("hardened_poop_brick_stairs", () -> new StairBlock(ModBlocks.HARDENED_POOP_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS)));
    public static final DeferredBlock<SlabBlock> HARDENED_POOP_BRICK_SLAB = registerBlock("hardened_poop_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB)));
    public static final DeferredBlock<WallBlock> HARDENED_POOP_BRICK_WALL = registerBlock("hardened_poop_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));
    public static final DeferredBlock<FilterBlock> FILTER = registerBlock("filter", () -> new FilterBlock(BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WOOD).ignitedByLava()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
