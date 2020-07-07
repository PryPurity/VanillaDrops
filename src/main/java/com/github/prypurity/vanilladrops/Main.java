package com.github.prypurity.vanilladrops;

import com.github.prypurity.vanilladrops.Listeners.deathevents;
import com.github.prypurity.vanilladrops.Listeners.entitybreedevents;
import de.leonhard.storage.LightningBuilder;
import de.leonhard.storage.Yaml;
import de.leonhard.storage.internal.settings.ConfigSettings;
import de.leonhard.storage.internal.settings.DataType;
import de.leonhard.storage.internal.settings.ReloadSettings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {
    public static Yaml mobdrops;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(" VanillaDrops has been enabled.");
        Bukkit.getConsoleSender().sendMessage(" Developed by: PryPurity ");
        Bukkit.getConsoleSender().sendMessage(" Vi Veri Veniversum Vivus Vici. ");
        Bukkit.getConsoleSender().sendMessage(" ");
        setupshit();
        setuplistener();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void setupshit() {
        mobdrops = LightningBuilder.fromFile(new File("plugins/VanillaDrops/mobs"))
                .addInputStreamFromResource("mobs.yml")
                .setConfigSettings(ConfigSettings.PRESERVE_COMMENTS)
                .setReloadSettings(ReloadSettings.AUTOMATICALLY)
                .setDataType(DataType.SORTED)
                .createConfig();
    }

    public void setuplistener() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(deathevents.INSTANCE, this);
        pm.registerEvents(entitybreedevents.INSTANCE, this);
    }
}
