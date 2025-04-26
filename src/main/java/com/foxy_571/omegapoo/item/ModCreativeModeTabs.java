package com.foxy_571.omegapoo.item;

import com.foxy_571.omegapoo.OmegaPoo;
import com.foxy_571.omegapoo.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OmegaPoo.MOD_ID);

    public static final Supplier<CreativeModeTab> OMEGAPOO_TAB = CREATIVE_MODE_TABS.register("omegapoo_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.POOP.get()))
            .title(Component.translatable("creativetab.omegapoo.omegapoo_tab"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.POOP);
                output.accept(ModItems.GOLDEN_POOP);
                output.accept(ModItems.OMEGA_POOP);
                output.accept(ModItems.POOP_ESSENCE);
                output.accept(ModItems.RAW_NUTRIENTS);
                output.accept(ModItems.POOP_IRON_INGOT);
                output.accept(ModItems.POOP_IRON_NUGGET);
                output.accept(ModItems.TOILET_PAPER);
                output.accept(ModItems.OMEGA_TOILET_PAPER);
                output.accept(ModItems.POOP_IRON_SWORD);
                output.accept(ModItems.POOP_IRON_SHOVEL);
                output.accept(ModItems.POOP_IRON_PICKAXE);
                output.accept(ModItems.POOP_IRON_AXE);
                output.accept(ModItems.POOP_IRON_HOE);
                output.accept(ModItems.PLUNGER);
                output.accept(ModItems.BOWL_OF_POOP);

                output.accept(ModBlocks.POOP_BLOCK);
                output.accept(ModBlocks.POOP_CARPET);
                output.accept(ModBlocks.HARDENED_POOP);
                output.accept(ModBlocks.HARDENED_POOP_STAIRS);
                output.accept(ModBlocks.HARDENED_POOP_SLAB);
                output.accept(ModBlocks.HARDENED_POOP_BRICKS);
                output.accept(ModBlocks.CHISELED_HARDENED_POOP_BRICKS);
                output.accept(ModBlocks.HARDENED_POOP_BRICK_STAIRS);
                output.accept(ModBlocks.HARDENED_POOP_BRICK_SLAB);
                output.accept(ModBlocks.HARDENED_POOP_BRICK_WALL);
                output.accept(ModBlocks.POOP_IRON_BLOCK);
                output.accept(ModBlocks.FILTER);
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
