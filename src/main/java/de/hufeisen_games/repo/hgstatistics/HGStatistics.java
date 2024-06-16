package main.java.de.hufeisen_games.repo.hgstatistics;

import main.java.de.hufeisen_games.repo.hgstatistics.commands.LeaderboardCommand;
import main.java.de.hufeisen_games.repo.hgstatistics.listener.BufferUpdateListener;
import main.java.de.hufeisen_games.repo.hgstatistics.listener.PlayerJoinListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HGStatistics extends JavaPlugin {

    public static HGStatistics plugin;

    public void onEnable() {
        plugin = this;

        Bukkit.getConsoleSender().sendMessage("§6Initialising HG-Statistics...");

        init(Bukkit.getPluginManager());

        Bukkit.getConsoleSender().sendMessage("§aHG-Statistics initialised!");

        StatisticBuffer.loadIntoCache();
    }

    private void init(PluginManager pluginManager) {
        LeaderboardCommand lc = new LeaderboardCommand();

        // BStats
        new Metrics(this, 14013);

        // Commands
        getCommand("leaderboard").setExecutor(lc);
        getCommand("leaderboard").setTabCompleter(lc);

        // Listener
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new BufferUpdateListener(), plugin);
    }

    public static HGStatistics getPlugin() {
        return plugin;
    }

}
