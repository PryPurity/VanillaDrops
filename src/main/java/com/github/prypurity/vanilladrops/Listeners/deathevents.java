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
    @Deprecated
    public void onEntityDeath(EntityDeathEvent de) {
        EntityType entityType = de.getEntityType();
        Yaml mobs = Main.mobdrops;
        String typename = Utils.titleCase(" ", entityType.name().toLowerCase().replaceAll("_", " ")) + ".";
        LivingEntity le = de.getEntity();


        // If Statement here to deal with Exp & CustomExp, since it is compatible with all mob types, and won't be needed for each mob.
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
            } else {
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
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropString.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropString.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropString.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.STRING, i));
                            break;
                        }
                    }
                }
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
                if (le instanceof Ageable) {
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
                        if (Main.mobdrops.getBoolean(typename + "CustomFeathers.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomFeathers.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomFeathers.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.FEATHER, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropChicken.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropChicken.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropChicken.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.CHICKEN, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropFeathers.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropFeathers.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropFeathers.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.FEATHER, i));
                            break;
                        }
                    }
                }
            case COD:
                if (!Main.mobdrops.getBoolean(typename + "Cod")) {
                    de.getDrops().remove(new ItemStack(Material.COD));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomCod.Enable") && !Main.mobdrops.getBoolean(typename + "Cod")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomCod.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomCod.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.COD, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Bonemeal")) {
                    de.getDrops().remove(new ItemStack(Material.BONE_MEAL));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomBonemeal.Enable") && !Main.mobdrops.getBoolean(typename + "Bonemeal")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomBonemeal.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomBonemeal.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.BONE_MEAL, i));
                    break;
                }
            case COW:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "RawBeef")) {
                            de.getDrops().remove(new ItemStack(Material.BEEF));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomBeef.Enable") && !Main.mobdrops.getBoolean(typename + "RawBeef")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomBeef.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomBeef.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.BEEF, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "Leather")) {
                            de.getDrops().remove(new ItemStack(Material.LEATHER));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomLeather.Enable") && !Main.mobdrops.getBoolean(typename + "Leather")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomLeather.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomLeather.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.LEATHER, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropBeef.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropBeef.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropBeef.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.BEEF, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropLeather.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropLeather.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropLeather.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.LEATHER, i));
                            break;
                        }
                    }
                }
            case CREEPER:
                if (!Main.mobdrops.getBoolean(typename + "Gunpowder")) {
                    de.getDrops().remove(new ItemStack(Material.GUNPOWDER));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomGunpowder.Enable") && !Main.mobdrops.getBoolean(typename + "Gunpowder")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomGunpowder.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomGunpowder.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.GUNPOWDER, i));
                    break;
                }
            case DOLPHIN:
                if (!Main.mobdrops.getBoolean(typename + "Cod")) {
                    de.getDrops().remove(new ItemStack(Material.COD));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomCod.Enable") && !Main.mobdrops.getBoolean(typename + "Cod")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomCod.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomCod.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.COD, i));
                    break;
                }
            case DONKEY:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "Leather")) {
                            de.getDrops().remove(new ItemStack(Material.LEATHER));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomLeather.Enable") && !Main.mobdrops.getBoolean(typename + "Leather")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomLeather.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomLeather.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.LEATHER, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropLeather.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropLeather.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropLeather.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.LEATHER, i));
                            break;
                        }
                    }
                }
            case DROWNED:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "RottenFlesh")) {
                            de.getDrops().remove(new ItemStack(Material.ROTTEN_FLESH));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomRottenFlesh.Enable") && !Main.mobdrops.getBoolean(typename + "RottenFlesh")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomRottenFlesh.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomRottenFlesh.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "GoldIngot")) {
                            de.getDrops().remove(new ItemStack(Material.GOLD_INGOT));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomGoldIngot.Enable") && !Main.mobdrops.getBoolean(typename + "GoldIngot")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomGoldIngot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomGoldIngot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.GOLD_INGOT, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "Trident")) {
                            de.getDrops().remove(new ItemStack(Material.TRIDENT));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomTrident.Enable") && !Main.mobdrops.getBoolean(typename + "Trident")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomTrident.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomTrident.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.TRIDENT, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "FishingRod")) {
                            de.getDrops().remove(new ItemStack(Material.FISHING_ROD));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomFishingRods.Enable") && !Main.mobdrops.getBoolean(typename + "FishingRod")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomFishingRods.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomFishingRods.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.FISHING_ROD, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "NautilusShell")) {
                            de.getDrops().remove(new ItemStack(Material.NAUTILUS_SHELL));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomNS.Enable") && !Main.mobdrops.getBoolean(typename + "NautilusShell")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomNS.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomNS.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.NAUTILUS_SHELL, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropRottenFlesh.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenFlesh.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenFlesh.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropGoldIngot.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomGoldIngot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomGoldIngot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.GOLD_INGOT, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropTrident.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomTrident.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomTrident.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.TRIDENT, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropFishingRods.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomFishingRods.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomFishingRods.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.FISHING_ROD, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropNS.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomNS.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomNS.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.NAUTILUS_SHELL, i));
                            break;
                        }
                    }
                }
            case ELDER_GUARDIAN:
                if (!Main.mobdrops.getBoolean(typename + "PrismarineShard")) {
                    de.getDrops().remove(new ItemStack(Material.PRISMARINE_SHARD));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomPS.Enable") && !Main.mobdrops.getBoolean(typename + "PrismarineShard")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomPS.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomPS.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.PRISMARINE_SHARD, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "PrismarineCrystal")) {
                    de.getDrops().remove(new ItemStack(Material.PRISMARINE_CRYSTALS));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomPC.Enable") && !Main.mobdrops.getBoolean(typename + "PrismarineCrystal")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomPC.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomPC.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.PRISMARINE_CRYSTALS, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Cod")) {
                    de.getDrops().remove(new ItemStack(Material.COD));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomCod.Enable") && !Main.mobdrops.getBoolean(typename + "Cod")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomCod.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomCod.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.COD, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "PufferFish")) {
                    de.getDrops().remove(new ItemStack(Material.PUFFERFISH));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomPufferFish.Enable") && !Main.mobdrops.getBoolean(typename + "PufferFish")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomPufferFish.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomPufferFish.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.PUFFERFISH, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Salmon")) {
                    de.getDrops().remove(new ItemStack(Material.SALMON));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomSalmon.Enable") && !Main.mobdrops.getBoolean(typename + "Salmon")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomSalmon.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomSalmon.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.SALMON, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "TropicalFish")) {
                    de.getDrops().remove(new ItemStack(Material.TROPICAL_FISH));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomTropicalFish.Enable") && !Main.mobdrops.getBoolean(typename + "TropicalFish")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomTropicalFish.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomTropicalFish.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.TROPICAL_FISH, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "WetSponge")) {
                    de.getDrops().remove(new ItemStack(Material.WET_SPONGE));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomWetSponge.Enable") && !Main.mobdrops.getBoolean(typename + "WetSponge")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomWetSponge.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomWetSponge.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.WET_SPONGE, i));
                    break;
                }
            case ENDERMAN:
                if (!Main.mobdrops.getBoolean(typename + "EnderPearl")) {
                    de.getDrops().remove(new ItemStack(Material.WET_SPONGE));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomEnderPearl.Enable") && !Main.mobdrops.getBoolean(typename + "EnderPearl")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomEnderPearl.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomEnderPearl.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.WET_SPONGE, i));
                    break;
                }
            case EVOKER:
                if (!Main.mobdrops.getBoolean(typename + "TotemOfUndying")) {
                    de.getDrops().remove(new ItemStack(Material.TOTEM_OF_UNDYING));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomToU.Enable") && !Main.mobdrops.getBoolean(typename + "TotemOfUndying")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomToU.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomToU.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.TOTEM_OF_UNDYING, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Emeralds")) {
                    de.getDrops().remove(new ItemStack(Material.EMERALD));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomEmeralds.Enable") && !Main.mobdrops.getBoolean(typename + "Emeralds")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomEmeralds.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomEmeralds.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.EMERALD, i));
                    break;
                }
            case GHAST:
                if (!Main.mobdrops.getBoolean(typename + "GhastTears")) {
                    de.getDrops().remove(new ItemStack(Material.GHAST_TEAR));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomTears.Enable") && !Main.mobdrops.getBoolean(typename + "GhastTears")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomTears.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomTears.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.GHAST_TEAR, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Gunpowder")) {
                    de.getDrops().remove(new ItemStack(Material.GUNPOWDER));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomGunpowder.Enable") && !Main.mobdrops.getBoolean(typename + "Gunpowder")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomGunpowder.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomGunpowder.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.GUNPOWDER, i));
                    break;
                }
            case GUARDIAN:
                if (!Main.mobdrops.getBoolean(typename + "PrismarineShard")) {
                    de.getDrops().remove(new ItemStack(Material.PRISMARINE_SHARD));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomPS.Enable") && !Main.mobdrops.getBoolean(typename + "PrismarineShard")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomPS.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomPS.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.PRISMARINE_SHARD, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "PrismarineCrystal")) {
                    de.getDrops().remove(new ItemStack(Material.PRISMARINE_CRYSTALS));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomPC.Enable") && !Main.mobdrops.getBoolean(typename + "PrismarineCrystal")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomPC.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomPC.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.PRISMARINE_CRYSTALS, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Cod")) {
                    de.getDrops().remove(new ItemStack(Material.COD));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomCod.Enable") && !Main.mobdrops.getBoolean(typename + "Cod")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomCod.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomCod.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.COD, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "PufferFish")) {
                    de.getDrops().remove(new ItemStack(Material.PUFFERFISH));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomPufferFish.Enable") && !Main.mobdrops.getBoolean(typename + "PufferFish")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomPufferFish.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomPufferFish.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.PUFFERFISH, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Salmon")) {
                    de.getDrops().remove(new ItemStack(Material.SALMON));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomSalmon.Enable") && !Main.mobdrops.getBoolean(typename + "Salmon")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomSalmon.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomSalmon.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.SALMON, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "TropicalFish")) {
                    de.getDrops().remove(new ItemStack(Material.TROPICAL_FISH));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomTropicalFish.Enable") && !Main.mobdrops.getBoolean(typename + "TropicalFish")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomTropicalFish.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomTropicalFish.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.TROPICAL_FISH, i));
                    break;
                }
            case HOGLIN:
                if (!Main.mobdrops.getBoolean(typename + "PorkChops")) {
                    de.getDrops().remove(new ItemStack(Material.PORKCHOP));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomPorkChops.Enable") && !Main.mobdrops.getBoolean(typename + "PorkChops")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomPorkChops.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomPorkChops.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.PORKCHOP, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Leather")) {
                    de.getDrops().remove(new ItemStack(Material.LEATHER));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomLeather.Enable") && !Main.mobdrops.getBoolean(typename + "Leather")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomLeather.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomLeather.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.LEATHER, i));
                    break;
                }
            case HORSE:
                if (!Main.mobdrops.getBoolean(typename + "Leather")) {
                    de.getDrops().remove(new ItemStack(Material.LEATHER));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomLeather.Enable") && !Main.mobdrops.getBoolean(typename + "Leather")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomLeather.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomLeather.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.LEATHER, i));
                    break;
                }
            case HUSK:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "RottenFlesh")) {
                            de.getDrops().remove(new ItemStack(Material.ROTTEN_FLESH));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomRottenFlesh.Enable") && !Main.mobdrops.getBoolean(typename + "RottenFlesh")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomRottenFlesh.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomRottenFlesh.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "IronIngot")) {
                            de.getDrops().remove(new ItemStack(Material.IRON_INGOT));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomIronIngot.Enable") && !Main.mobdrops.getBoolean(typename + "IronIngot")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomIronIngot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomIronIngot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.IRON_INGOT, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "Carrot")) {
                            de.getDrops().remove(new ItemStack(Material.CARROT));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomCarrot.Enable") && !Main.mobdrops.getBoolean(typename + "Carrot")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomCarrot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomCarrot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.CARROT, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "Potato")) {
                            de.getDrops().remove(new ItemStack(Material.POTATO));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomPotato.Enable") && !Main.mobdrops.getBoolean(typename + "Potato")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomPotato.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomPotato.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.POTATO, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropRottenFlesh.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenFlesh.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenFlesh.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropIronIngot.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomGoldIngot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomGoldIngot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.IRON_INGOT, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropCarrot.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomTrident.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomTrident.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.CARROT, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropPotato.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomFishingRods.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomFishingRods.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.POTATO, i));
                            break;
                        }
                    }
                }
            case ILLUSIONER:
                if (!Main.mobdrops.getBoolean(typename + "Bow")) {
                    de.getDrops().remove(new ItemStack(Material.BOW));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomBow.Enable") && !Main.mobdrops.getBoolean(typename + "Bow")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomBow.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomBow.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.BOW, i));
                    break;
                }
            case IRON_GOLEM:
                if (!Main.mobdrops.getBoolean(typename + "IronIngots")) {
                    de.getDrops().remove(new ItemStack(Material.IRON_INGOT));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomIronIngots.Enable") && !Main.mobdrops.getBoolean(typename + "IronIngots")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomIronIngots.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomIronIngots.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.IRON_INGOT, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Roses")) {
                    de.getDrops().remove(new ItemStack(Material.POPPY));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomRoses.Enable") && !Main.mobdrops.getBoolean(typename + "Roses")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomRoses.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomRoses.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.POPPY, i));
                    break;
                }
            case LLAMA:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "Leather")) {
                            de.getDrops().remove(new ItemStack(Material.LEATHER));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomLeather.Enable") && !Main.mobdrops.getBoolean(typename + "Leather")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomLeather.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomLeather.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.LEATHER, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropLeather.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropLeather.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropLeather.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.LEATHER, i));
                            break;
                        }
                    }
                }
            case MAGMA_CUBE:
                if (!Main.mobdrops.getBoolean(typename + "Magmacreams")) {
                    de.getDrops().remove(new ItemStack(Material.MAGMA_CREAM));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomMagmacreams.Enable") && !Main.mobdrops.getBoolean(typename + "Magmacreams")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomMagmacreams.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomMagmacreams.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.MAGMA_CREAM, i));
                    break;
                }
            case MULE:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "Leather")) {
                            de.getDrops().remove(new ItemStack(Material.LEATHER));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomLeather.Enable") && !Main.mobdrops.getBoolean(typename + "Leather")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomLeather.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomLeather.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.LEATHER, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropLeather.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropLeather.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropLeather.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.LEATHER, i));
                            break;
                        }
                    }
                }
            case MUSHROOM_COW:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "RawBeef")) {
                            de.getDrops().remove(new ItemStack(Material.BEEF));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomBeef.Enable") && !Main.mobdrops.getBoolean(typename + "RawBeef")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomBeef.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomBeef.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.BEEF, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "Leather")) {
                            de.getDrops().remove(new ItemStack(Material.LEATHER));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomLeather.Enable") && !Main.mobdrops.getBoolean(typename + "Leather")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomLeather.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomLeather.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.LEATHER, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropBeef.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropBeef.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropBeef.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.BEEF, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropLeather.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropLeather.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropLeather.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.LEATHER, i));
                            break;
                        }
                    }
                }
            case PANDA:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "Bamboo")) {
                            de.getDrops().remove(new ItemStack(Material.BAMBOO));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomBamboo.Enable") && !Main.mobdrops.getBoolean(typename + "Bamboo")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomBamboo.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomBamboo.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.BAMBOO, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropBamboo.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropBamboo.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropBamboo.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.BAMBOO, i));
                            break;
                        }
                    }
                }
            case PARROT:
                if (!Main.mobdrops.getBoolean(typename + "Feathers")) {
                    de.getDrops().remove(new ItemStack(Material.FEATHER));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomFeathers.Enable") && !Main.mobdrops.getBoolean(typename + "Feathers")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomFeathers.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomFeathers.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.FEATHER, i));
                    break;
                }
            case PHANTOM:
                if (!Main.mobdrops.getBoolean(typename + "PhantomMembranes")) {
                    de.getDrops().remove(new ItemStack(Material.PHANTOM_MEMBRANE));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomMembranes.Enable") && !Main.mobdrops.getBoolean(typename + "PhantomMembranes")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomMembranes.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomMembranes.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.PHANTOM_MEMBRANE, i));
                    break;
                }
            case PIG:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "Porkchops")) {
                            de.getDrops().remove(new ItemStack(Material.PORKCHOP));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomPorkchops.Enable") && !Main.mobdrops.getBoolean(typename + "Porkchops")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomPorkchops.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomPorkchops.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.PORKCHOP, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropPorkchops.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropPorkchops.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropPorkchops.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.PORKCHOP, i));
                            break;
                        }
                    }
                }
            case PILLAGER:
                if (!Main.mobdrops.getBoolean(typename + "Arrows")) {
                    de.getDrops().remove(new ItemStack(Material.ARROW));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomArrows.Enable") && !Main.mobdrops.getBoolean(typename + "Arrows")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomArrows.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomArrows.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.ARROW, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Crossbows")) {
                    de.getDrops().remove(new ItemStack(Material.CROSSBOW));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomCrossBow.Enable") && !Main.mobdrops.getBoolean(typename + "Crossbows")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomCrossBow.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomCrossBow.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.CROSSBOW, i));
                    break;
                }
            case POLAR_BEAR:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "Cod")) {
                            de.getDrops().remove(new ItemStack(Material.COD));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomCod.Enable") && !Main.mobdrops.getBoolean(typename + "Cod")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomCod.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomCod.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.COD, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "Salmon")) {
                            de.getDrops().remove(new ItemStack(Material.SALMON));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomSalmon.Enable") && !Main.mobdrops.getBoolean(typename + "Salmon")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomSalmon.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomSalmon.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.SALMON, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropCod.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropCod.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropCod.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.COD, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropSalmon.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropSalmon.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropSalmon.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.SALMON, i));
                            break;
                        }
                    }
                }
            case PUFFERFISH:
                if (!Main.mobdrops.getBoolean(typename + "Pufferfish")) {
                    de.getDrops().remove(new ItemStack(Material.PUFFERFISH));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomPufferfish.Enable") && !Main.mobdrops.getBoolean(typename + "Pufferfish")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomPufferfish.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomPufferfish.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.PUFFERFISH, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Bonemeal")) {
                    de.getDrops().remove(new ItemStack(Material.BONE_MEAL));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomBonemeal.Enable") && !Main.mobdrops.getBoolean(typename + "Bonemeal")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomBonemeal.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomBonemeal.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.BONE_MEAL, i));
                    break;
                }
            case RABBIT:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "RawRabbit")) {
                            de.getDrops().remove(new ItemStack(Material.RABBIT));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomRawRabbit.Enable") && !Main.mobdrops.getBoolean(typename + "RawRabbit")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomRawRabbit.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomRawRabbit.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.RABBIT, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "RabbitHide")) {
                            de.getDrops().remove(new ItemStack(Material.RABBIT_HIDE));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomRabbitHide.Enable") && !Main.mobdrops.getBoolean(typename + "RabbitHide")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomRabbitHide.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomRabbitHide.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.RABBIT_HIDE, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "RabbitsFoot")) {
                            de.getDrops().remove(new ItemStack(Material.RABBIT_FOOT));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomRabbitsFoot.Enable") && !Main.mobdrops.getBoolean(typename + "RabbitsFoot")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomRabbitsFoot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomRabbitsFoot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.RABBIT_FOOT, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropRawRabbit.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropRawRabbit.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropRawRabbit.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.RABBIT, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropRabbitHide.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropRabbitHide.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropRabbitHide.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.RABBIT_HIDE, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropRabbitsFoot.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropRabbitsFoot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropRabbitsFoot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.RABBIT_FOOT, i));
                            break;
                        }
                    }
                }
            case RAVAGER:
                if (!Main.mobdrops.getBoolean(typename + "Saddle")) {
                    de.getDrops().remove(new ItemStack(Material.SADDLE));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomSaddle.Enable") && !Main.mobdrops.getBoolean(typename + "Saddle")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomSaddle.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomSaddle.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.SADDLE, i));
                    break;
                }
            case SALMON:
                if (!Main.mobdrops.getBoolean(typename + "Salmon")) {
                    de.getDrops().remove(new ItemStack(Material.SALMON));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomSalmon.Enable") && !Main.mobdrops.getBoolean(typename + "Salmon")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomSalmon.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomSalmon.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.SALMON, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Bonemeal")) {
                    de.getDrops().remove(new ItemStack(Material.BONE_MEAL));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomBonemeal.Enable") && !Main.mobdrops.getBoolean(typename + "Bonemeal")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomBonemeal.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomBonemeal.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.BONE_MEAL, i));
                    break;
                }
            case SHEEP:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "Wool")) {
                            de.getDrops().remove(new ItemStack(Material.LEGACY_WOOL));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomWool.Enable") && !Main.mobdrops.getBoolean(typename + "Wool")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomWool.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomWool.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.LEGACY_WOOL, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "RawMutton")) {
                            de.getDrops().remove(new ItemStack(Material.MUTTON));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomMutton.Enable") && !Main.mobdrops.getBoolean(typename + "RawMutton")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomMutton.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomMutton.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.MUTTON, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropCustomWool.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropCustomWool.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropCustomWool.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.LEGACY_WOOL, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropCustomMutton.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropCustomMutton.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropCustomMutton.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.MUTTON, i));
                            break;
                        }
                    }
                }
            case SHULKER:
                if (!Main.mobdrops.getBoolean(typename + "ShulkerShells")) {
                    de.getDrops().remove(new ItemStack(Material.SHULKER_SHELL));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomShulkerShells.Enable") && !Main.mobdrops.getBoolean(typename + "ShulkerShells")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomShulkerShells.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomShulkerShells.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.SHULKER_SHELL, i));
                    break;
                }
            case SKELETON:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "Arrows")) {
                            de.getDrops().remove(new ItemStack(Material.ARROW));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomArrows.Enable") && !Main.mobdrops.getBoolean(typename + "Arrows")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomArrows.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomArrows.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ARROW, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "Bones")) {
                            de.getDrops().remove(new ItemStack(Material.BONE));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomBones.Enable") && !Main.mobdrops.getBoolean(typename + "Bones")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomBones.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomBones.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.BONE, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "Bows")) {
                            de.getDrops().remove(new ItemStack(Material.BOW));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomBows.Enable") && !Main.mobdrops.getBoolean(typename + "Bows")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomBows.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomBows.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.BOW, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropArrows.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenFlesh.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenFlesh.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ARROW, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropBones.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomGoldIngot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomGoldIngot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.BONE, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropBows.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomTrident.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomTrident.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.BOW, i));
                            break;
                        }
                    }
                }
            case SKELETON_HORSE:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "Bones")) {
                            de.getDrops().remove(new ItemStack(Material.BONE));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomBones.Enable") && !Main.mobdrops.getBoolean(typename + "Bones")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomBones.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomBones.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.BONE, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropBones.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropBones.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropBones.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.BONE, i));
                            break;
                        }
                    }
                }
            case SLIME:
                if (!Main.mobdrops.getBoolean(typename + "Slimeballs")) {
                    de.getDrops().remove(new ItemStack(Material.SLIME_BALL));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomSlimeballs.Enable") && !Main.mobdrops.getBoolean(typename + "Slimeballs")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomSlimeballs.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomSlimeballs.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.SLIME_BALL, i));
                    break;
                }
            case SNOWMAN:
                if (!Main.mobdrops.getBoolean(typename + "Snowballs")) {
                    de.getDrops().remove(new ItemStack(Material.SNOWBALL));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomSnowballs.Enable") && !Main.mobdrops.getBoolean(typename + "Snowballs")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomSnowballs.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomSnowballs.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.SNOWBALL, i));
                    break;
                }
            case SPIDER:
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
            case SQUID:
                if (!Main.mobdrops.getBoolean(typename + "Inksacs")) {
                    de.getDrops().remove(new ItemStack(Material.INK_SAC));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomInksacs.Enable") && !Main.mobdrops.getBoolean(typename + "Inksacs")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomInksacs.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomInksacs.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.INK_SAC, i));
                    break;
                }
                // This shit kinda annoying, lmao
            case STRAY:
                if (!Main.mobdrops.getBoolean(typename + "Arrows")) {
                    de.getDrops().remove(new ItemStack(Material.ARROW));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomArrows.Enable") && !Main.mobdrops.getBoolean(typename + "Arrows")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomArrows.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomArrows.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.ARROW, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Bones")) {
                    de.getDrops().remove(new ItemStack(Material.BONE));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomBones.Enable") && !Main.mobdrops.getBoolean(typename + "Bones")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomBones.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomBones.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.BONE, i));
                    break;
                }
            case STRIDER:
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
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropString.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropString.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropString.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.STRING, i));
                            break;
                        }
                    }
                }
            case TRADER_LLAMA:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "Leather")) {
                            de.getDrops().remove(new ItemStack(Material.LEATHER));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomLeather.Enable") && !Main.mobdrops.getBoolean(typename + "Leather")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomLeather.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomLeather.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.LEATHER, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropLeather.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropLeather.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropLeather.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.LEATHER, i));
                            break;
                        }
                    }
                }
            case TROPICAL_FISH:
                if (!Main.mobdrops.getBoolean(typename + "Tropicalfish")) {
                    de.getDrops().remove(new ItemStack(Material.TROPICAL_FISH));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomTropicalfish.Enable") && !Main.mobdrops.getBoolean(typename + "Tropicalfish")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomTropicalfish.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomTropicalfish.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.TROPICAL_FISH, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Bonemeal")) {
                    de.getDrops().remove(new ItemStack(Material.BONE_MEAL));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomBonemeal.Enable") && !Main.mobdrops.getBoolean(typename + "Bonemeal")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomBonemeal.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomBonemeal.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.BONE_MEAL, i));
                    break;
                }
            case TURTLE:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "Seagrass")) {
                            de.getDrops().remove(new ItemStack(Material.SEAGRASS));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomSeagrass.Enable") && !Main.mobdrops.getBoolean(typename + "Seagrass")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomSeagrass.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomSeagrass.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.SEAGRASS, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesSeagrass.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesSeagrass.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesSeagrass.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.SEAGRASS, i));
                            break;
                        }
                    }
                }
            case VINDICATOR:
                if (!Main.mobdrops.getBoolean(typename + "Emeralds")) {
                    de.getDrops().remove(new ItemStack(Material.EMERALD));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomEmeralds.Enable") && !Main.mobdrops.getBoolean(typename + "Emeralds")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomEmeralds.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomEmeralds.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.EMERALD, i));
                    break;
                }
            case WITCH:
                if (!Main.mobdrops.getBoolean(typename + "GlassBottle")) {
                    de.getDrops().remove(new ItemStack(Material.GLASS_BOTTLE));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomGlassBottle.Enable") && !Main.mobdrops.getBoolean(typename + "GlassBottle")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomGlassBottle.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomGlassBottle.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.GLASS_BOTTLE, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "GlowstoneDust")) {
                    de.getDrops().remove(new ItemStack(Material.GLOWSTONE_DUST));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomGlowstoneDust.Enable") && !Main.mobdrops.getBoolean(typename + "GlowstoneDust")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomGlowstoneDust.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomGlowstoneDust.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.GLOWSTONE_DUST, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Gunpowder")) {
                    de.getDrops().remove(new ItemStack(Material.GUNPOWDER));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomGunpowder.Enable") && !Main.mobdrops.getBoolean(typename + "Gunpowder")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomGunpowder.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomGunpowder.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.GUNPOWDER, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Redstone")) {
                    de.getDrops().remove(new ItemStack(Material.REDSTONE));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomRedstone.Enable") && !Main.mobdrops.getBoolean(typename + "Redstone")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomRedstone.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomRedstone.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.REDSTONE, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "SpiderEye")) {
                    de.getDrops().remove(new ItemStack(Material.SPIDER_EYE));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomSpiderEye.Enable") && !Main.mobdrops.getBoolean(typename + "SpiderEye")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomSpiderEye.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomSpiderEye.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.SPIDER_EYE, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Stick")) {
                    de.getDrops().remove(new ItemStack(Material.STICK));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomStick.Enable") && !Main.mobdrops.getBoolean(typename + "Stick")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomStick.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomStick.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.STICK, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Sugar")) {
                    de.getDrops().remove(new ItemStack(Material.SUGAR));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomSugar.Enable") && !Main.mobdrops.getBoolean(typename + "Sugar")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomSugar.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomSugar.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.SUGAR, i));
                    break;
                }
            case WITHER:
                if (!Main.mobdrops.getBoolean(typename + "NetherStar")) {
                    de.getDrops().remove(new ItemStack(Material.NETHER_STAR));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomNetherStar.Enable") && !Main.mobdrops.getBoolean(typename + "NetherStar")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomNetherStar.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomNetherStar.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.NETHER_STAR, i));
                    break;
                }
            case WITHER_SKELETON:
                if (!Main.mobdrops.getBoolean(typename + "Coal")) {
                    de.getDrops().remove(new ItemStack(Material.COAL));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomCoal.Enable") && !Main.mobdrops.getBoolean(typename + "Coal")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomCoal.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomCoal.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.COAL, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "Bones")) {
                    de.getDrops().remove(new ItemStack(Material.BONE));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomBones.Enable") && !Main.mobdrops.getBoolean(typename + "Bones")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomBones.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomBones.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.BONE, i));
                    break;
                }
                if (!Main.mobdrops.getBoolean(typename + "WitherSkulls")) {
                    de.getDrops().remove(new ItemStack(Material.WITHER_SKELETON_SKULL));
                }
                if (Main.mobdrops.getBoolean(typename + "CustomWitherSkulls.Enable") && !Main.mobdrops.getBoolean(typename + "WitherSkulls")) {
                    Random r = ThreadLocalRandom.current();
                    int min = Main.mobdrops.getInt(typename + "CustomWitherSkulls.amountMin");
                    int max = Main.mobdrops.getInt(typename + "CustomWitherSkulls.amountMax");
                    int i = r.nextInt(((max - min) + 1) + min);
                    de.getDrops().add(new ItemStack(Material.WITHER_SKELETON_SKULL, i));
                    break;
                }
            case ZOGLIN:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "Rottenflesh")) {
                            de.getDrops().remove(new ItemStack(Material.ROTTEN_FLESH));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomRottenflesh.Enable") && !Main.mobdrops.getBoolean(typename + "Rottenflesh")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomRottenflesh.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomRottenflesh.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropRottenFlesh.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenFlesh.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenFlesh.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, i));
                            break;
                        }
                    }
                }
            case ZOMBIE:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "RottenFlesh")) {
                            de.getDrops().remove(new ItemStack(Material.ROTTEN_FLESH));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomRottenFlesh.Enable") && !Main.mobdrops.getBoolean(typename + "RottenFlesh")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomRottenFlesh.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomRottenFlesh.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "IronIngot")) {
                            de.getDrops().remove(new ItemStack(Material.IRON_INGOT));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomIronIngot.Enable") && !Main.mobdrops.getBoolean(typename + "IronIngot")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomIronIngot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomIronIngot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.IRON_INGOT, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "Carrot")) {
                            de.getDrops().remove(new ItemStack(Material.CARROT));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomCarrot.Enable") && !Main.mobdrops.getBoolean(typename + "Carrot")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomCarrot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomCarrot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.CARROT, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "Potato")) {
                            de.getDrops().remove(new ItemStack(Material.POTATO));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomPotato.Enable") && !Main.mobdrops.getBoolean(typename + "Potato")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomPotato.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomPotato.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.POTATO, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropRottenFlesh.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenFlesh.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenFlesh.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropIronIngot.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropIronIngot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropIronIngot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.IRON_INGOT, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropCarrot.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropCarrot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropCarrot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.CARROT, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropPotato.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropPotato.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropPotato.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.POTATO, i));
                            break;
                        }
                    }
                }
            case ZOMBIE_HORSE:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "Rottenflesh")) {
                            de.getDrops().remove(new ItemStack(Material.ROTTEN_FLESH));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomRottenflesh.Enable") && !Main.mobdrops.getBoolean(typename + "Rottenflesh")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomRottenflesh.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomRottenflesh.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropRottenflesh.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenflesh.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenflesh.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, i));
                            break;
                        }
                    }
                }
            case ZOMBIFIED_PIGLIN:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "Rottenflesh")) {
                            de.getDrops().remove(new ItemStack(Material.ROTTEN_FLESH));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomRottenflesh.Enable") && !Main.mobdrops.getBoolean(typename + "Rottenflesh")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomRottenflesh.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomRottenflesh.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "Goldnugget")) {
                            de.getDrops().remove(new ItemStack(Material.GOLD_NUGGET));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomGoldnugget.Enable") && !Main.mobdrops.getBoolean(typename + "Goldnugget")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomGoldnugget.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomGoldnugget.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.GOLD_NUGGET, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "Goldingot")) {
                            de.getDrops().remove(new ItemStack(Material.GOLD_INGOT));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomGoldingot.Enable") && !Main.mobdrops.getBoolean(typename + "Goldingot")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomGoldingot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomGoldingot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.GOLD_INGOT, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropRottenflesh.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenflesh.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenflesh.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropGoldnugget.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropGoldnugget.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropGoldnugget.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.GOLD_NUGGET, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropGoldingot.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropGoldingot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropGoldingot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.GOLD_INGOT, i));
                            break;
                        }
                    }
                }
            case ZOMBIE_VILLAGER:
                if (le instanceof Ageable) {
                    if (((Ageable) le).isAdult()) {
                        if (!Main.mobdrops.getBoolean(typename + "RottenFlesh")) {
                            de.getDrops().remove(new ItemStack(Material.ROTTEN_FLESH));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomRottenFlesh.Enable") && !Main.mobdrops.getBoolean(typename + "RottenFlesh")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomRottenFlesh.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomRottenFlesh.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "IronIngot")) {
                            de.getDrops().remove(new ItemStack(Material.IRON_INGOT));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomIronIngot.Enable") && !Main.mobdrops.getBoolean(typename + "IronIngot")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomIronIngot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomIronIngot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.IRON_INGOT, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "Carrot")) {
                            de.getDrops().remove(new ItemStack(Material.CARROT));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomCarrot.Enable") && !Main.mobdrops.getBoolean(typename + "Carrot")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomCarrot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomCarrot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.CARROT, i));
                            break;
                        }
                        if (!Main.mobdrops.getBoolean(typename + "Potato")) {
                            de.getDrops().remove(new ItemStack(Material.POTATO));
                        }
                        if (Main.mobdrops.getBoolean(typename + "CustomPotato.Enable") && !Main.mobdrops.getBoolean(typename + "Potato")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "CustomPotato.amountMin");
                            int max = Main.mobdrops.getInt(typename + "CustomPotato.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.POTATO, i));
                            break;
                        }
                    } else {
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropRottenFlesh.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenFlesh.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropRottenFlesh.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropIronIngot.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropIronIngot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropIronIngot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.IRON_INGOT, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropCarrot.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropCarrot.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropCarrot.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.CARROT, i));
                            break;
                        }
                        if (Main.mobdrops.getBoolean(typename + "DeadBabiesDropPotato.Enable")) {
                            Random r = ThreadLocalRandom.current();
                            int min = Main.mobdrops.getInt(typename + "DeadBabiesDropPotato.amountMin");
                            int max = Main.mobdrops.getInt(typename + "DeadBabiesDropPotato.amountMax");
                            int i = r.nextInt(((max - min) + 1) + min);
                            de.getDrops().add(new ItemStack(Material.POTATO, i));
                            break;
                        }
                    }
                }
                break;
        }
    }
}