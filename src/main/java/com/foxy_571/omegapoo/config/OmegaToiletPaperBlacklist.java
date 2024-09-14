package com.foxy_571.omegapoo.config;

import com.foxy_571.omegapoo.OmegaPoo;
import com.google.gson.stream.JsonReader;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.loading.FMLPaths;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class OmegaToiletPaperBlacklist {
    private static final File FILE = new File(getConfigDir(), "/omegapoo/omega_toilet_paper_blacklist.txt");

    public static void write() { // Creates blacklist file
        try {
            if (!FILE.exists()) {
                FileWriter writer = new FileWriter(FILE);
                writer.write("minecraft:air\n");
                writer.write("minecraft:command_block\n");
                writer.write("minecraft:repeating_command_block\n");
                writer.write("minecraft:chain_command_block\n");
                writer.write("minecraft:command_block_minecart\n");
                writer.write("minecraft:debug_stick\n");
                writer.close();
            }
        }
        catch (IOException exception) {
            OmegaPoo.LOGGER.error("Failed to write omega_toilet_paper_blacklist.txt");
        }
    }

    public static Set<ResourceLocation> get() { // returns all resource locations on blacklist as a Set
        Set<ResourceLocation> itemKeys = new HashSet<>();
        try {
            Scanner reader = new Scanner(FILE);
            while (reader.hasNextLine()) {
                String keyName = reader.nextLine();
                if (keyName.endsWith(":")) { // If name is mod ID, add all keys from that mod ID
                    for (ResourceLocation key : BuiltInRegistries.ITEM.keySet()) {
                        if (key.toString().startsWith(keyName)) {
                            itemKeys.add(key);
                        }
                    }
                }
                else {
                    itemKeys.add(ResourceLocation.parse(keyName));
                }
            }
            reader.close();
        }
        catch (IOException exception) {
            OmegaPoo.LOGGER.debug("Failed to load omega_toilet_paper_blacklist.txt");
        }
        return itemKeys;
    }

    public static String getConfigDir() { // returns the Minecraft Config directory
        return FMLPaths.CONFIGDIR.get().toString();
    }
}
