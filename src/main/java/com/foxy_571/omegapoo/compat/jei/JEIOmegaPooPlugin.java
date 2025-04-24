package com.foxy_571.omegapoo.compat.jei;

import com.foxy_571.omegapoo.OmegaPoo;
import com.foxy_571.omegapoo.block.ModBlocks;
import com.foxy_571.omegapoo.recipe.FilterRecipe;
import com.foxy_571.omegapoo.recipe.ModRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JeiPlugin
public class JEIOmegaPooPlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(OmegaPoo.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(@NotNull IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new FilterRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<FilterRecipe> filterRecipes = recipeManager.getAllRecipesFor(ModRecipes.FILTER_RECIPE_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(FilterRecipeCategory.FILTER_RECIPE_TYPE, filterRecipes);
    }

    @Override
    public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.FILTER), FilterRecipeCategory.FILTER_RECIPE_TYPE);
    }
}
