package main.java.de.hufeisen_games.repo.hgstatistics;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import main.java.de.hufeisen_games.repo.hgstatistics.commands.LeaderboardCommand;
import main.java.de.hufeisen_games.repo.hgstatistics.listener.PlayerJoinListener;

public class HGStatistics extends JavaPlugin {

	public static HGStatistics plugin;

	public void onEnable() {
		plugin = this;

		Bukkit.getConsoleSender().sendMessage("§6Initialising HG-Statistics...");

		init(Bukkit.getPluginManager());

		Bukkit.getConsoleSender().sendMessage("§aHG-Statistics initialised!");
	}

	private void init(PluginManager pluginManager) {
		LeaderboardCommand lc = new LeaderboardCommand();
		
		// BStats
        new Metrics(this, 14013);

		// Befehle
		getCommand("leaderboard").setExecutor(lc);
		getCommand("leaderboard").setTabCompleter(lc);
		
		// Listener
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), plugin);
	}

	public static HGStatistics getPlugin() {
		return plugin;
	}

}
