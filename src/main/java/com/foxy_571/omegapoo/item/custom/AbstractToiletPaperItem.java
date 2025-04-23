package com.foxy_571.omegapoo.item.custom;

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

public abstract class AbstractToiletPaperItem extends Item {
    public AbstractToiletPaperItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide) {
            if (player.isCrouching() && (player.isCreative() || player.getFoodData().getFoodLevel() > 0)) {
                player.addItem(getPoopedItem());
                player.causeFoodExhaustion(10);
                level.playSound(null, player.getOnPos(), ModSounds.POOP.get(), SoundSource.PLAYERS, 1F, new Random().nextFloat(0.5F, 1.5F));
                if (!player.isCreative()) {
                    affectItemStack(player.getItemInHand(usedHand), player);
                }
                player.getCooldowns().addCooldown(this, getCooldown());
                return InteractionResultHolder.success(player.getItemInHand(usedHand));
            }
        }
        return InteractionResultHolder.fail(player.getItemInHand(usedHand));
    }

    /** Returns the item given to the player when the toilet paper item is used. */
    protected abstract ItemStack getPoopedItem();

    /** Returns the cooldown of the toilet paper item. */
    protected abstract int getCooldown();

    /**
     * API for affecting the toilet paper once it is used.
     *
     * @param itemStack the toilet paper item stack
     * @param player the Player that used the toilet paper item stack
     */
    protected void affectItemStack(ItemStack itemStack, Player player) {}

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.omegapoo.toilet_paper"));
    }
}