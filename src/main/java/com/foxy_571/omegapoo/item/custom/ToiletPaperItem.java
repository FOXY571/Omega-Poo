package com.foxy_571.omegapoo.item.custom;

import com.foxy_571.omegapoo.config.Config;
import com.foxy_571.omegapoo.item.ModItems;
import com.foxy_571.omegapoo.sound.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class ToiletPaperItem extends Item {
    public ToiletPaperItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide) {
            if (player.isCrouching() && (player.isCreative() || player.getFoodData().getFoodLevel() > 0)) {
                player.addItem(new ItemStack(new Random().nextFloat() < (Config.goldenPoopChance / 100f) ? ModItems.GOLDEN_POOP.get() : ModItems.POOP.get()));
                player.causeFoodExhaustion(10);
                level.playSound(null, player.getOnPos(), ModSounds.POOP.get(), SoundSource.PLAYERS, 1f, new Random().nextFloat(0.5F, 1.5F));
                if (!player.isCreative()) {
                    player.getItemInHand(usedHand).shrink(1);
                }
                player.getCooldowns().addCooldown(this, Config.toiletPaperCooldown);
                return InteractionResultHolder.success(player.getItemInHand(usedHand));
            }
        }
        return InteractionResultHolder.fail(player.getItemInHand(usedHand));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.omegapoo.toilet_paper"));
    }
}