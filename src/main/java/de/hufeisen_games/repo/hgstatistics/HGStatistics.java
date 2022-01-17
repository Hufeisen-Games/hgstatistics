package main.java.de.hufeisen_games.repo.hgstatistics;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import main.java.de.hufeisen_games.repo.hgstatistics.commands.LeaderboardCommand;

public class HGStatistics extends JavaPlugin {

	public static HGStatistics plugin;

	public void onEnable() {
		plugin = this;

		Bukkit.getConsoleSender().sendMessage("§6Initialisiere HG-Statistics...");

		init(Bukkit.getPluginManager());

		Bukkit.getConsoleSender().sendMessage("§aHG-Statistics geladen!");
	}

	private void init(PluginManager pluginManager) {
		LeaderboardCommand lc = new LeaderboardCommand();
		
		// Configs

		// Befehle
		getCommand("leaderboard").setExecutor(lc);
		getCommand("leaderboard").setTabCompleter(lc);
		
		// Listener

	}

	public static HGStatistics getPlugin() {
		return plugin;
	}

}
