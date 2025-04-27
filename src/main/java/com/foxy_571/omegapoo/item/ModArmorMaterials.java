package com.foxy_571.omegapoo.item;

import com.foxy_571.omegapoo.OmegaPoo;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final Holder<ArmorMaterial> POOP_IRON_ARMOR_MATERIAL = register("poop_iron", Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
        attribute.put(ArmorItem.Type.BOOTS, 2);
        attribute.put(ArmorItem.Type.LEGGINGS, 5);
        attribute.put(ArmorItem.Type.CHESTPLATE, 6);
        attribute.put(ArmorItem.Type.HELMET, 2);
        attribute.put(ArmorItem.Type.BODY, 8);
    }), 16, SoundEvents.ARMOR_EQUIP_IRON, 1, 0, ModItems.POOP_IRON_INGOT);

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> defense, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Item> repairItem) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(OmegaPoo.MOD_ID, name);
        Supplier<Ingredient> ingredient = () -> Ingredient.of(repairItem.get());
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location, new ArmorMaterial(defense, enchantmentValue, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}
