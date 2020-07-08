package com.github.prypurity.vanilladrops.Listeners;

import com.github.prypurity.vanilladrops.Main;
import com.github.prypurity.vanilladrops.utils.Utils;
import de.leonhard.storage.Yaml;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public enum deathevents implements Listener {
    INSTANCE;

    @EventHandler
    public void onEntityDeath(EntityDeathEvent de) {
        final EntityType entityType = de.getEntityType();
        final Yaml mobs = Main.mobdrops;
        final String typename = Utils.titleCase(" ", entityType.name().toLowerCase().replaceAll("_", " ")) + ".";
        LivingEntity le = de.getEntity();
        Ageable age = (Ageable) le;

        if (!Main.mobdrops.getBoolean(typename + "Exp")) {
            de.setDroppedExp(0);
        }
        Random r = ThreadLocalRandom.current();
        if (age.isAdult()) {
            if (Main.mobdrops.getBoolean(typename + "CustomExp.Enable") && !Main.mobdrops.getBoolean(typename + "Exp")) {
                int min = Main.mobdrops.getInt(typename + "CustomExp.expMin");
                int max = Main.mobdrops.getInt(typename + "CustomExp.expMax");
                int i = r.nextInt((max - min) + 1) + min;
                de.setDroppedExp(i);
            }
        }
        if (!age.isAdult()) {
            if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropEXP.Enable")) {
                int min = Main.mobdrops.getInt(typename + "DeadBabiesDropEXP.expMin");
                int max = Main.mobdrops.getInt(typename + "DeadBabiesDropEXP.expMax");
                int i = r.nextInt((max - min) + 1) + min;
                de.setDroppedExp(i);
            }
        }
    }
}