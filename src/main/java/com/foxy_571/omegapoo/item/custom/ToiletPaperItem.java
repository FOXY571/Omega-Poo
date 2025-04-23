package com.foxy_571.omegapoo.item.custom;

import com.foxy_571.omegapoo.config.Config;
import com.foxy_571.omegapoo.item.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Random;

public class ToiletPaperItem extends AbstractToiletPaperItem {
    public ToiletPaperItem(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemStack getPoopedItem() {
        return new ItemStack(new Random().nextFloat() < (Config.goldenPoopChance / 100F) ? ModItems.GOLDEN_POOP.get() : ModItems.POOP.get());
    }

    @Override
    protected int getCooldown() {
        return Config.toiletPaperCooldown;
    }

    @Override
    protected void affectItemStack(ItemStack itemStack, Player player) {
        itemStack.shrink(1);
    }
}