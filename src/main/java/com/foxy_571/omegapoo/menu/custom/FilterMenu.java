package com.foxy_571.omegapoo.menu.custom;

import com.foxy_571.omegapoo.block.ModBlocks;
import com.foxy_571.omegapoo.block.entity.FilterBlockEntity;
import com.foxy_571.omegapoo.menu.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class FilterMenu extends AbstractContainerMenu {
    public final FilterBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public FilterMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public FilterMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ContainerData data) {
        super(ModMenus.FILTER_MENU.get(), containerId);
        this.blockEntity = (FilterBlockEntity) blockEntity;
        this.level = inventory.player.level();
        this.data = data;

        addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 0, 56, 17));
        addSlot(new FilterResultSlotItemHandler(this.blockEntity.itemHandler, 1, 56, 53));
        addSlot(new FilterResultSlotItemHandler(this.blockEntity.itemHandler, 2, 116, 35));

        int k;
        for(k = 0; k < 3; ++k) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }

        for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
        }

        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledArrowProgress() {
        int progress = data.get(0);
        int maxProgress = data.get(1);
        int arrowPixelSize = 24;

        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot.hasItem()) {
            ItemStack slotItemStack = slot.getItem();
            itemstack = slotItemStack.copy();
            if (index == 1 || index == 2) {
                if (!moveItemStackTo(slotItemStack, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index != 0) {
                if (!this.moveItemStackTo(slotItemStack, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackTo(slotItemStack, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (slotItemStack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (slotItemStack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, slotItemStack);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.FILTER.get());
    }
}
