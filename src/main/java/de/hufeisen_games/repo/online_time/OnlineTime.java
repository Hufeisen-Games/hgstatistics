package main.java.de.hufeisen_games.repo.online_time;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import main.java.de.hufeisen_games.repo.online_time.commands.ShowOnlineTimeCommand;
import main.java.de.hufeisen_games.repo.online_time.commands.ShowWalkedBlocks;

public class OnlineTime extends JavaPlugin {

	public static OnlineTime plugin;

	public void onEnable() {
		plugin = this;

		Bukkit.getConsoleSender().sendMessage("§6Initialisiere OnlineTime...");

		init(Bukkit.getPluginManager());

		Bukkit.getConsoleSender().sendMessage("§aOnlineTime geladen!");
	}

	private void init(PluginManager pluginManager) {
		// Configs

		// Befehle
		getCommand("ShowOnlineTime").setExecutor(new ShowOnlineTimeCommand());
		getCommand("ShowBlocksWalked").setExecutor(new ShowWalkedBlocks());
		
		// Listener

	}

	public static OnlineTime getPlugin() {
		return plugin;
	}

}
