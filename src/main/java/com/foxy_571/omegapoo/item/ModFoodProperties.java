package com.foxy_571.omegapoo.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class ModFoodProperties {
    public static final FoodProperties POOP = new FoodProperties.Builder().nutrition(3).saturationModifier(0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 450), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 300, 2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300), 1.0F).build();

    public static final FoodProperties BOWL_OF_POOP = new FoodProperties.Builder().nutrition(7).saturationModifier(0.2F)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 255), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 150, 2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 150), 1.0F)
            .usingConvertsTo(Items.BOWL).build();
}
