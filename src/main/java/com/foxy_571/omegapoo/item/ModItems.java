package com.foxy_571.omegapoo.item;

import com.foxy_571.omegapoo.OmegaPoo;
import com.foxy_571.omegapoo.item.custom.OmegaToiletPaperItem;
import com.foxy_571.omegapoo.item.custom.PlungerItem;
import com.foxy_571.omegapoo.item.custom.ToiletPaperItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(OmegaPoo.MOD_ID);

    public static final DeferredItem<Item> POOP = ITEMS.register("poop", () -> new Item(new Item.Properties().food(ModFoodProperties.POOP)));
    public static final DeferredItem<Item> GOLDEN_POOP = ITEMS.register("golden_poop", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OMEGA_POOP = ITEMS.register("omega_poop", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final DeferredItem<Item> POOP_ESSENCE = ITEMS.register("poop_essence", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_NUTRIENTS = ITEMS.register("raw_nutrients", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POOP_IRON_INGOT = ITEMS.register("poop_iron_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POOP_IRON_NUGGET = ITEMS.register("poop_iron_nugget", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TOILET_PAPER = ITEMS.register("toilet_paper", () -> new ToiletPaperItem(new Item.Properties()));
    public static final DeferredItem<Item> OMEGA_TOILET_PAPER = ITEMS.register("omega_toilet_paper", () -> new OmegaToiletPaperItem(new Item.Properties().durability(100).rarity(Rarity.UNCOMMON).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final DeferredItem<SwordItem> POOP_IRON_SWORD = ITEMS.register("poop_iron_sword", () -> new SwordItem(ModToolTiers.POOP_IRON, new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.POOP_IRON, 3, -2.4F))));
    public static final DeferredItem<ShovelItem> POOP_IRON_SHOVEL = ITEMS.register("poop_iron_shovel", () -> new ShovelItem(ModToolTiers.POOP_IRON, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.POOP_IRON, 1.5F, -3))));
    public static final DeferredItem<PickaxeItem> POOP_IRON_PICKAXE = ITEMS.register("poop_iron_pickaxe", () -> new PickaxeItem(ModToolTiers.POOP_IRON, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.POOP_IRON, 1, -2.8F))));
    public static final DeferredItem<AxeItem> POOP_IRON_AXE = ITEMS.register("poop_iron_axe", () -> new AxeItem(ModToolTiers.POOP_IRON, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.POOP_IRON, 6, -3))));
    public static final DeferredItem<HoeItem> POOP_IRON_HOE = ITEMS.register("poop_iron_hoe", () -> new HoeItem(ModToolTiers.POOP_IRON, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.POOP_IRON, -2.25F, -0.75F))));
    public static final DeferredItem<Item> PLUNGER = ITEMS.register("plunger", () -> new PlungerItem(new Item.Properties().durability(238).component(DataComponents.TOOL, PlungerItem.createToolProperties())));
    public static final DeferredItem<ArmorItem> POOP_IRON_HELMET = ITEMS.register("poop_iron_helmet", () -> new ArmorItem(ModArmorMaterials.POOP_IRON_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(23))));
    public static final DeferredItem<ArmorItem> POOP_IRON_CHESTPLATE = ITEMS.register("poop_iron_chestplate", () -> new ArmorItem(ModArmorMaterials.POOP_IRON_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(23))));
    public static final DeferredItem<ArmorItem> POOP_IRON_LEGGINGS = ITEMS.register("poop_iron_leggings", () -> new ArmorItem(ModArmorMaterials.POOP_IRON_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(23))));
    public static final DeferredItem<ArmorItem> POOP_IRON_BOOTS = ITEMS.register("poop_iron_boots", () -> new ArmorItem(ModArmorMaterials.POOP_IRON_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(23))));
    public static final DeferredItem<Item> BOWL_OF_POOP = ITEMS.register("bowl_of_poop", () -> new Item(new Item.Properties().stacksTo(1).food(ModFoodProperties.BOWL_OF_POOP)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
