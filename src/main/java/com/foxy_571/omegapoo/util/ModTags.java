package com.foxy_571.omegapoo.util;

import com.foxy_571.omegapoo.OmegaPoo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_POOP_IRON_TOOL = createTag("needs_poop_iron_tool");
        public static final TagKey<Block> INCORRECT_FOR_POOP_IRON_TOOL = createTag("incorrect_for_poop_iron_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(OmegaPoo.MOD_ID, name));
        }
    }
}
