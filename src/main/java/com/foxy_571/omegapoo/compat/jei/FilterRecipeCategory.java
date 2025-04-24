package com.foxy_571.omegapoo.compat.jei;

import com.foxy_571.omegapoo.OmegaPoo;
import com.foxy_571.omegapoo.block.ModBlocks;
import com.foxy_571.omegapoo.recipe.FilterRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FilterRecipeCategory implements IRecipeCategory<FilterRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(OmegaPoo.MOD_ID, "filtering");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(OmegaPoo.MOD_ID, "textures/gui/container/filter.png");

    public static final RecipeType<FilterRecipe> FILTER_RECIPE_TYPE = new RecipeType<>(UID, FilterRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public FilterRecipeCategory(IGuiHelper helper) {
        background = helper.createDrawable(TEXTURE, 55, 16, 82, 54);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.FILTER));
    }

    @Override
    public @NotNull RecipeType<FilterRecipe> getRecipeType() {
        return FILTER_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("jei_title.omegapoo.filtering");
    }

    @Override
    @Nullable
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull FilterRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(recipe.getIngredients().getFirst());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 19).addItemStack(recipe.output());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 1, 37).addItemStack(recipe.waste());
    }
}
