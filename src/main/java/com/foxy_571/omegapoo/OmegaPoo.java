package com.foxy_571.omegapoo;

import com.foxy_571.omegapoo.block.ModBlocks;
import com.foxy_571.omegapoo.config.Config;
import com.foxy_571.omegapoo.config.OmegaToiletPaperBlacklist;
import com.foxy_571.omegapoo.item.ModCreativeModeTabs;
import com.foxy_571.omegapoo.item.ModItems;
import com.foxy_571.omegapoo.sound.ModSounds;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(OmegaPoo.MOD_ID)
public class OmegaPoo {
    public static final String MOD_ID = "omegapoo";
    public static final Logger LOGGER = LogUtils.getLogger();

    public OmegaPoo(IEventBus modEventBus, ModContainer modContainer) {
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC, "omegapoo/omegapoo.toml");
        OmegaToiletPaperBlacklist.write();
    }
}
