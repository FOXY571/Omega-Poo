package com.foxy_571.omegapoo.block.entity;

import com.foxy_571.omegapoo.OmegaPoo;
import com.foxy_571.omegapoo.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, OmegaPoo.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FilterBlockEntity>> FILTER = BLOCK_ENTITIES.register("filter", () -> BlockEntityType.Builder.of(FilterBlockEntity::new, ModBlocks.FILTER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
