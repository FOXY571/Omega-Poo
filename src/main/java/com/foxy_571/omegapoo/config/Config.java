package com.foxy_571.omegapoo.config;

import com.foxy_571.omegapoo.OmegaPoo;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = OmegaPoo.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.IntValue TOILET_PAPER_COOLDOWN = BUILDER.comment("How long in ticks should players have to wait before using Toilet Paper again? (default: 20)").defineInRange("Toilet Paper Cooldown", 20, 20, 300);
    private static final ModConfigSpec.IntValue OMEGA_TOILET_PAPER_COOLDOWN = BUILDER.comment("How long in ticks should players have to wait before using Omega Toilet Paper again? (default: 50)").defineInRange("Omega Toilet Paper Cooldown", 50, 20, 300);
    private static final ModConfigSpec.IntValue GOLDEN_POOP_CHANCE = BUILDER.comment("What should the percent chance to get Golden Poop be when using Toilet Paper? (default: 1)").defineInRange("Golden Poop Chance", 1, 1, 100);

    public static final ModConfigSpec SPEC = BUILDER.build();

    public static Integer toiletPaperCooldown;
    public static Integer omegaToiletPaperCooldown;
    public static Integer goldenPoopChance;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        toiletPaperCooldown = TOILET_PAPER_COOLDOWN.get();
        omegaToiletPaperCooldown = OMEGA_TOILET_PAPER_COOLDOWN.get();
        goldenPoopChance = GOLDEN_POOP_CHANCE.get();
    }
}
