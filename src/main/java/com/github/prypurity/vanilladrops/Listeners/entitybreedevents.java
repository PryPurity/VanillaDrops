package com.github.prypurity.vanilladrops.Listeners;

import com.github.prypurity.vanilladrops.Main;
import com.github.prypurity.vanilladrops.utils.Utils;
import de.leonhard.storage.Yaml;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public enum entitybreedevents implements Listener {
    INSTANCE;

    @EventHandler
    public void onEntityBreed(EntityBreedEvent be) {
        final EntityType entityType = be.getEntityType();
        Yaml mobs = Main.mobdrops;
        final String typename = Utils.titleCase(" ", entityType.name().toLowerCase().replaceAll("_", " ")) + ".";

        if (Main.mobdrops.getBoolean(typename + "CustomBreedExp.Enable") && !Main.mobdrops.getBoolean(typename + "BreedingExp")) {
            Random r = ThreadLocalRandom.current();
            int min = Main.mobdrops.getInt(typename + "CustomBreedExp.expMin");
            int max = Main.mobdrops.getInt(typename + "CustomBreedExp.expMax");
            int i = r.nextInt((max - min) + 1) + min;
            be.setExperience(i);
        }
    }
}
