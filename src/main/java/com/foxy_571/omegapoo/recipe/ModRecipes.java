package com.foxy_571.omegapoo.recipe;

import com.foxy_571.omegapoo.OmegaPoo;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, OmegaPoo.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, OmegaPoo.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<FilterRecipe>> FILTER_SERIALIZER = SERIALIZERS.register("filter", FilterRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<FilterRecipe>> FILTER_RECIPE_TYPE = RECIPE_TYPES.register("filter", () -> new RecipeType<>() {
        @Override
        public String toString() {
            return "filter";
        }
    });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }
}
