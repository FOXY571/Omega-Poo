package com.foxy_571.omegapoo.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record FilterRecipe(Ingredient inputItem, ItemStack output) implements Recipe<SingleRecipeInput> {
    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(inputItem);
        return ingredients;
    }

    @Override
    public boolean matches(@NotNull SingleRecipeInput singleRecipeInput, @NotNull Level level) {
        if (!level.isClientSide()) {
            return inputItem.test(singleRecipeInput.getItem(0));
        }
        return false;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SingleRecipeInput singleRecipeInput, HolderLookup.@NotNull Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int value) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider provider) {
        return output;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.FILTER_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModRecipes.FILTER_RECIPE_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<FilterRecipe> {
        public static final MapCodec<FilterRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(FilterRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(FilterRecipe::output)
        ).apply(inst, FilterRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, FilterRecipe> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC, FilterRecipe::inputItem,
                ItemStack.STREAM_CODEC, FilterRecipe::output,
                FilterRecipe::new
        );

        @Override
        public @NotNull MapCodec<FilterRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, FilterRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
