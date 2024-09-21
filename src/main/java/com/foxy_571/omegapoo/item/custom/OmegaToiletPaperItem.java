package com.foxy_571.omegapoo.item.custom;

import com.foxy_571.omegapoo.OmegaPoo;
import com.foxy_571.omegapoo.config.Config;
import com.foxy_571.omegapoo.config.OmegaToiletPaperBlacklist;
import com.foxy_571.omegapoo.sound.ModSounds;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class OmegaToiletPaperItem extends Item {
    public OmegaToiletPaperItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide) {
            if (player.isCrouching() && (player.isCreative() || player.getFoodData().getFoodLevel() > 0)) {
                player.addItem(getRandomItem());
                player.causeFoodExhaustion(10);
                level.playSound(null, player.getOnPos(), ModSounds.POOP.get(), SoundSource.PLAYERS, 1f, new Random().nextFloat(0.5F, 1.5F));
                if (!player.isCreative()) {
                    player.getItemInHand(usedHand).hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                }
                player.getCooldowns().addCooldown(this, Config.omegaToiletPaperCooldown);
                return InteractionResultHolder.success(player.getItemInHand(usedHand));
            }
        }
        return InteractionResultHolder.fail(player.getItemInHand(usedHand));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.omegapoo.toilet_paper.tooltip"));
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