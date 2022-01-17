package main.java.de.hufeisen_games.repo.name;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Name extends JavaPlugin {

	public static Name plugin;

	public void onEnable() {
		plugin = this;

		Bukkit.getConsoleSender().sendMessage("§6Initialisiere Name...");

		init(Bukkit.getPluginManager());

		Bukkit.getConsoleSender().sendMessage("§aName geladen!");
	}

	private void init(PluginManager pluginManager) {
		// Configs

		// Befehle

		// Listener

	}

	public static Name getPlugin() {
		return plugin;
	}

}
