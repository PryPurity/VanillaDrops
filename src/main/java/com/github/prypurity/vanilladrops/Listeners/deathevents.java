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
        EntityType entityType = de.getEntityType();
        Yaml mobs = Main.mobdrops;
        String typename = Utils.titleCase(" ", entityType.name().toLowerCase().replaceAll("_", " ")) + ".";
        LivingEntity le = de.getEntity();
        if (!Main.mobdrops.getBoolean(typename + "Exp")) {
            de.setDroppedExp(0);
        }
        if (le instanceof Ageable) {
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
                    System.out.println("Dead " + typename.replace(".", "") + "drop exp");
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
                if (!Main.mobdrops.getBoolean(typename + "BlazeRods")) {
                    de.getDrops().remove(new ItemStack(Material.BLAZE_ROD));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomRods.Enable") && !Main.mobdrops.getBoolean(typename + "BlazeRods")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomRods.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomRods.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.BLAZE_ROD, i));
                    break;
                }
                break;
            case CAT:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "String")) {
                            de.getDrops().remove(new ItemStack(Material.STRING));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomString.Enable") && !Main.mobdrops.getBoolean(typename + "String")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomString.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomString.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.STRING, i));
                            break;
                        }
                    }
                    if (!((Ageable) le).isAdult()) {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropString.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropString.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropString.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.STRING, i));
                            break;
                        }
                        break;
                    }
                }
                break;
            case CAVE_SPIDER:
                if (!Main.mobdrops.getBoolean(typename + "SpiderEyes")) {
                    de.getDrops().remove(new ItemStack(Material.SPIDER_EYE));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomSpiderEyes.Enable") && !Main.mobdrops.getBoolean(typename + "SpiderEyes")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomSpiderEyes.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomSpiderEyes.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.SPIDER_EYE, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "String")) {
                    de.getDrops().remove(new ItemStack(Material.STRING));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomString.Enable") && !Main.mobdrops.getBoolean(typename + "String")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomString.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomString.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.STRING, i));
                    break;
                }
                break;
            case CHICKEN:
                if (((Ageable) le).isAdult()) {
                    if (!Main.mobdrops.getBoolean(typename + "RawChicken")) {
                        de.getDrops().remove(new ItemStack(Material.CHICKEN));
                    }
                    if (Main.mobdrops.getBoolean(typename + "CustomChicken.Enable") && !Main.mobdrops.getBoolean(typename + "RawChicken")) {
                        Random r = ThreadLocalRandom.current();
                        int min = Main.mobdrops.getInt(typename + "CustomChicken.amountMin");
                        int max = Main.mobdrops.getInt(typename + "CustomChicken.amountMax");
                        int i = r.nextInt(((max - min) + 1) + min);
                        de.getDrops().add(new ItemStack(Material.CHICKEN, i));
                        break;
                    }
                    if (!Main.mobdrops.getBoolean(typename + "Feathers")) {
                        de.getDrops().remove(new ItemStack(Material.FEATHER));
                    }
                }
                if (!((Ageable) le).isAdult()) {
                    if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropString.Enable")) {
                        Random r = ThreadLocalRandom.current();
                        int min = Main.mobdrops.getInt(typename + "DeadBabiesDropString.amountMin");
                        int max = Main.mobdrops.getInt(typename + "DeadBabiesDropString.amountMax");
                        int i = r.nextInt(((max - min) + 1) + min);
                        de.getDrops().add(new ItemStack(Material.STRING, i));
                        break;
                    }
                    if (!Main.mobdrops.getBoolean(typename + "SpiderEyes")) {
                        de.getDrops().remove(new ItemStack(Material.SPIDER_EYE));
                    }
                }
        }
    }
}