package com.foxy_571.omegapoo.item.custom;

import com.foxy_571.omegapoo.OmegaPoo;
import com.foxy_571.omegapoo.config.Config;
import com.foxy_571.omegapoo.config.OmegaToiletPaperBlacklist;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class OmegaToiletPaperItem extends AbstractToiletPaperItem {
    public OmegaToiletPaperItem(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemStack getPoopedItem() {
        return getRandomItem();
    }

    @Override
    protected int getCooldown() {
        return Config.omegaToiletPaperCooldown;
    }

    @Override
    protected void affectItemStack(ItemStack itemStack, Player player) {
        itemStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
    }

    @Override
    public boolean supportsEnchantment(@NotNull ItemStack stack, @NotNull Holder<Enchantment> enchantment) {
        return false;
    }

    private ItemStack getRandomItem() {
        List<ResourceLocation> itemKeys = new ArrayList<>(BuiltInRegistries.ITEM.keySet()); // Get all registered items
        Set<ResourceLocation> blacklistedItemKeys = OmegaToiletPaperBlacklist.get(); // Get all blacklisted items
        for (ResourceLocation key : blacklistedItemKeys) { // Removes all blacklisted items
            itemKeys.remove(key);
        }
        Random rng = new Random();
        int key = rng.nextInt(itemKeys.size());
        OmegaPoo.LOGGER.info("Spawned {} from Omega Toilet Paper", itemKeys.get(key));
        return new ItemStack(BuiltInRegistries.ITEM.get(itemKeys.get(key)));
    }
}