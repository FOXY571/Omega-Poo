package com.foxy_571.omegapoo.block.entity;

import com.foxy_571.omegapoo.OmegaPoo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class FilterBlockEntity extends BlockEntity {
    private int counter;

    public FilterBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.FILTER.get(), pos, blockState);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, @NotNull HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        CompoundTag omegapooData = tag.getCompound(OmegaPoo.MOD_ID);
        this.counter = omegapooData.getInt("Counter");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, @NotNull HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        CompoundTag omegapooData = new CompoundTag();
        omegapooData.putInt("Counter", this.counter);
        tag.put(OmegaPoo.MOD_ID, omegapooData);
    }

    public int incrementCounter() {
        this.counter++;
        setChanged();
        return counter;
    }
}
