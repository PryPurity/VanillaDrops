package com.github.prypurity.vanilladrops.Listeners;

import com.github.prypurity.vanilladrops.Main;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public enum entitydeathevents implements Listener {
    INSTANCE;

    @EventHandler
    public void onEntityDeath(EntityDeathEvent de) {
        LivingEntity le = de.getEntity();
        Random r = ThreadLocalRandom.current();
        if (le.getType() == EntityType.BAT) {
            if (Main.mobdrops.getBoolean("Bat.CustomExp.Enable")) {
                int min = Main.mobdrops.getInt("Bat.CustomExp.expMin");
                int max = Main.mobdrops.getInt("Bat.CustomExp.expMax");
                int i = r.nextInt((max - min) + 1) + min;
                de.setDroppedExp(i);
            }
        }
        if (le.getType() == EntityType.BEE) {
            if (!Main.mobdrops.getBoolean("Bee.Exp")) {
                de.setDroppedExp(0);
            }
            if (Main.mobdrops.getBoolean("Bee.CustomExp.Enable") && !Main.mobdrops.getBoolean("Bee.Exp")) {
                int min = Main.mobdrops.getInt("Bee.CustomExp.expMin");
                int max = Main.mobdrops.getInt("Bee.CustomExp.expMax");
                int i = r.nextInt((max - min) + 1) + min;
                de.setDroppedExp(i);
            }
            if (Main.mobdrops.getBoolean("Bee.DeadBabiesDropExp.Enable")) {
                int min = Main.mobdrops.getInt("Bee.DeadBabiesDropExp.expMin");
                int max = Main.mobdrops.getInt("Bee.DeadBabiesDropExp.expMax");
                int i = r.nextInt((max - min) + 1) + min;
                de.setDroppedExp(i);
            }
        }
        if (le instanceof Blaze) {
            if (!Main.mobdrops.getBoolean("Blaze.BlazeRods")) {
                de.getDrops().removeIf((ItemStack is) -> is.getType().equals(Material.BLAZE_ROD));
            }
            if (!Main.mobdrops.getBoolean("Blaze.Exp")) {
                de.setDroppedExp(0);
            }
            if (Main.mobdrops.getBoolean("Blaze.CustomRods.Enable") && !Main.mobdrops.getBoolean("Blaze.BlazeRods")) {
                int min = Main.mobdrops.getInt("Blaze.CustomRods.amountMin");
                int max = Main.mobdrops.getInt("Blaze.CustomRods.amountMax");
                int i = r.nextInt((max - min) + 1) + min;
                de.getDrops().add(i, new ItemStack(Material.BLAZE_ROD));
            }
            if (Main.mobdrops.getBoolean("Blaze.CustomExp.Enable") || !Main.mobdrops.getBoolean("Blaze.Exp")) {
                int min = Main.mobdrops.getInt("Blaze.CustomExp.expMin");
                int max = Main.mobdrops.getInt("Blaze.CustomExp.expMax");
                int i = r.nextInt((max - min) + 1) + min;
                de.setDroppedExp(i);
            }
        }
    }
}
