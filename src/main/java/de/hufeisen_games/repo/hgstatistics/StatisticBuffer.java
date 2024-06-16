package main.java.de.hufeisen_games.repo.hgstatistics;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class StatisticBuffer {

    private static HashMap<String, Integer> craftedItems = new HashMap<>();
    private static HashMap<String, Integer> brokenBlocks = new HashMap<>();
    private static HashMap<String, Integer> itemBroken = new HashMap<>();
    private static HashMap<String, Integer> killedEntities = new HashMap<>();

    public static boolean isLoading = false;

    public static void loadIntoCache() {

        isLoading = true;

        new BukkitRunnable() {

            @Override
            public void run() {

                Bukkit.getConsoleSender().sendMessage(Messages.PREFIX + "§6Loading statistics to cache...");

                for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {

                    int items = 0;

                    for (Material m : Material.values()) {

                        items += p.getStatistic(Statistic.CRAFT_ITEM, m);

                    }

                    craftedItems.put(p.getName(), items);
                }

                Bukkit.getConsoleSender().sendMessage(Messages.PREFIX + "§61/4 Finished...");

                for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {

                    int blocks = 0;

                    for (Material m : Material.values()) {

                        blocks += p.getStatistic(Statistic.MINE_BLOCK, m);

                    }

                    brokenBlocks.put(p.getName(), blocks);

                }

                Bukkit.getConsoleSender().sendMessage(Messages.PREFIX + "§62/4 Finished...");

                for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {

                    int items = 0;

                    for (Material m : Material.values()) {

                        items += p.getStatistic(Statistic.BREAK_ITEM, m);

                    }

                    itemBroken.put(p.getName(), items);

                }

                Bukkit.getConsoleSender().sendMessage(Messages.PREFIX + "§63/4 Finished...");

                for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {

                    int entities = 0;

                    for (EntityType e : EntityType.values()) {

                        if (e != EntityType.UNKNOWN) {
                            entities += p.getStatistic(Statistic.KILL_ENTITY, e);
                        }

                    }

                    killedEntities.put(p.getName(), entities);

                }
                Bukkit.getConsoleSender().sendMessage(Messages.PREFIX + "§64/4 Finished...");
                Bukkit.getConsoleSender().sendMessage(Messages.PREFIX + "§6Cache update complete!");
                isLoading = false;
            }

        }.runTaskAsynchronously(HGStatistics.getPlugin());

    }

    public static void addToCache(Statistic statistic, String playerName, int value) {

        if (statistic == Statistic.CRAFT_ITEM) {

            craftedItems.put(playerName, craftedItems.get(playerName) + value);

        } else if (statistic == Statistic.MINE_BLOCK) {

            brokenBlocks.put(playerName, brokenBlocks.get(playerName) + value);

        } else if (statistic == Statistic.BREAK_ITEM) {

            itemBroken.put(playerName, itemBroken.get(playerName) + value);

        } else if (statistic == Statistic.KILL_ENTITY) {

            killedEntities.put(playerName, killedEntities.get(playerName) + value);

        }

    }

    public static HashMap<String, Integer> getStatisticFromCache(Statistic statistic) {

        if (statistic == Statistic.CRAFT_ITEM) {

            return craftedItems;

        } else if (statistic == Statistic.MINE_BLOCK) {

            return brokenBlocks;

        } else if (statistic == Statistic.BREAK_ITEM) {

            return itemBroken;

        } else if (statistic == Statistic.KILL_ENTITY) {

            return killedEntities;

        }

        return null;
    }

}
