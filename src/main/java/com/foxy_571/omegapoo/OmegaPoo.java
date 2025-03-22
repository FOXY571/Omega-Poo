package com.foxy_571.omegapoo;

import com.foxy_571.omegapoo.block.ModBlocks;
import com.foxy_571.omegapoo.block.entity.ModBlockEntities;
import com.foxy_571.omegapoo.config.Config;
import com.foxy_571.omegapoo.config.OmegaToiletPaperBlacklist;
import com.foxy_571.omegapoo.item.ModCreativeModeTabs;
import com.foxy_571.omegapoo.item.ModItems;
import com.foxy_571.omegapoo.menu.ModMenus;
import com.foxy_571.omegapoo.menu.custom.FilterScreen;
import com.foxy_571.omegapoo.recipe.ModRecipes;
import com.foxy_571.omegapoo.sound.ModSounds;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
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
        ModBlockEntities.register(modEventBus);
        ModMenus.register(modEventBus);
        ModSounds.register(modEventBus);
        ModRecipes.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC, "omegapoo/omegapoo.toml");
        OmegaToiletPaperBlacklist.write();
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerMenus(RegisterMenuScreensEvent event) {
            event.register(ModMenus.FILTER_MENU.get(), FilterScreen::new);
        }
    }
}
