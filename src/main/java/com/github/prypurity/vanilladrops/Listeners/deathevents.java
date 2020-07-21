package com.github.prypurity.vanilladrops.Listeners;

import com.github.prypurity.vanilladrops.Main;
import com.github.prypurity.vanilladrops.utils.Utils;
import de.leonhard.storage.Yaml;
import org.bukkit.Material;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public enum deathevents implements Listener {
    INSTANCE;

    @EventHandler
    public void onEntityDeath(EntityDeathEvent de) {
        final EntityType entityType = de.getEntityType();
        Yaml mobs = Main.mobdrops;
        final String typename = Utils.titleCase(" ", entityType.name().toLowerCase().replaceAll("_", " ")) + ".";
        System.out.println(typename);
        LivingEntity le = de.getEntity();
        if (!Main.mobdrops.getBoolean(typename + "Exp")) {
            de.setDroppedExp(0);
        }
        if (le instanceof Ageable) {
            //System.out.println("CHECK TO SEE IF AGEABLE");
            if (((Ageable) le).isAdult()) {
                if (Main.mobdrops.getBoolean(typename + "CustomExp.Enable") && !Main.mobdrops.getBoolean(typename + "Exp")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomExp.expMin");
                    int max = Main.mobdrops.getInt(typename + "CustomExp.expMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.setDroppedExp(i);
                }
            }
            if (!((Ageable) le).isAdult()) {
                if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropEXP.Enable")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "DeadBabiesDropEXP.expMin");
                    int max = Main.mobdrops.getInt(typename + "DeadBabiesDropEXP.expMax");
                    int i = r.nextInt((max - min) + 1) + min;
                    de.setDroppedExp(i);
                }
            }
        } else {
            if (Main.mobdrops.getBoolean(typename + "CustomExp.Enable") && !Main.mobdrops.getBoolean(typename + "Exp")) {
                Random r = ThreadLocalRandom.current();
                int min = Main.mobdrops.getInt(typename + "CustomExp.expMin");
                int max = Main.mobdrops.getInt(typename + "CustomExp.expMax");
                int i = r.nextInt(((max - min) + 1) + min);
                de.setDroppedExp(i);
            }
        }

        // Switch case to deal with the mob type drops. Forgive me, for I am new to switch cases. =(
        switch (de.getEntity().getType()) {
            case BLAZE:
                System.out.println("Blaze it bitches");
                if (!Main.mobdrops.getBoolean(entityType + "BlazeRods")) {
                    de.getDrops().remove(new ItemStack(Material.BLAZE_ROD));
                }
                if (Main.mobdrops.getBoolean("CustomRods") && !Main.mobdrops.getBoolean("Blaze.BlazeRods")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(entityType + "CustomRods.expMin");
                    int max = Main.mobdrops.getInt(entityType + "CustomRods.expMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.setDroppedExp(i);
                    break;
                }
            case CAT:
                System.out.println("Boom kitty dead!");
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(entityType + "String")) {
                            de.getDrops().remove(new ItemStack(Material.STRING));
                            System.out.println("Cat String Disabled!");
                            break;
                        }
                        if (Main.mobdrops.getBoolean(entityType + "CustomString") && !Main.mobdrops.getBoolean("String")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(entityType + "CustomString.amountMin");
                            int max = Main.mobdrops.getInt(entityType + "CustomString.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.STRING, i));
                            System.out.println("Cat String Disabled, now using Custom String!");
                            break;
                        }
                    }
                    if (!((Ageable) le).isAdult()) {
                        if (Main.mobdrops.getBoolean(entityType + "DeadBabiesDropString")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(entityType + "DeadBabiesDropString.amountMin");
                            int max = Main.mobdrops.getInt(entityType + "DeadBabiesDropString.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.STRING, i));
                            System.out.println("haha, dead babies give string");
                            break;
                        }
                    }
                }
        }
    }
}