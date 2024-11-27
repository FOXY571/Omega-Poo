package com.foxy_571.omegapoo.item;

import com.foxy_571.omegapoo.OmegaPoo;
import com.foxy_571.omegapoo.item.custom.OmegaToiletPaperItem;
import com.foxy_571.omegapoo.item.custom.PlungerItem;
import com.foxy_571.omegapoo.item.custom.ToiletPaperItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(OmegaPoo.MOD_ID);

    public static final DeferredItem<Item> POOP = ITEMS.register("poop", () -> new Item(new Item.Properties().food(ModFoodProperties.POOP)));
    public static final DeferredItem<Item> GOLDEN_POOP = ITEMS.register("golden_poop", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OMEGA_POOP = ITEMS.register("omega_poop", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final DeferredItem<Item> TOILET_PAPER = ITEMS.register("toilet_paper", () -> new ToiletPaperItem(new Item.Properties()));
    public static final DeferredItem<Item> OMEGA_TOILET_PAPER = ITEMS.register("omega_toilet_paper", () -> new OmegaToiletPaperItem(new Item.Properties().durability(100).rarity(Rarity.UNCOMMON).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final DeferredItem<Item> PLUNGER = ITEMS.register("plunger", () -> new PlungerItem(new Item.Properties().durability(238).component(DataComponents.TOOL, PlungerItem.createToolProperties())));
    public static final DeferredItem<Item> BOWL_OF_POOP = ITEMS.register("bowl_of_poop", () -> new Item(new Item.Properties().stacksTo(1).food(ModFoodProperties.BOWL_OF_POOP)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
