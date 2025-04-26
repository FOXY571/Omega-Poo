package com.foxy_571.omegapoo.item;

import com.foxy_571.omegapoo.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier POOP_IRON = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_POOP_IRON_TOOL, 375, 6.5F, 2.25F, 14, () -> Ingredient.of(ModItems.POOP_IRON_INGOT));
}
