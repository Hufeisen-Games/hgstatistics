package main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import main.java.de.hufeisen_games.repo.hgstatistics.commands.LeaderboardCommand;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;

public class CraftItem implements SubCommand {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		HashMap<String, Integer> playerStatistics = new HashMap<>();

		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {

			int items = 0;

			for (Material m : Material.values()) {

				items += p.getStatistic(Statistic.CRAFT_ITEM, m);

			}

			playerStatistics.put(p.getName(), items);

		}

		LeaderboardCommand.sendStatistics(sender, "Items Crafted", playerStatistics);

		return false;
	}

}
