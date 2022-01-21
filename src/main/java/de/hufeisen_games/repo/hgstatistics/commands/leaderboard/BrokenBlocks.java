package main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import main.java.de.hufeisen_games.repo.hgstatistics.Messages;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.LeaderboardCommand;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;

public class BrokenBlocks implements SubCommand {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		HashMap<String, Integer> playerStatistics = new HashMap<>();

		sender.sendMessage(Messages.LAGG_WARNING);
		
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {

			int blocks = 0;

			for (Material m : Material.values()) {
				
				blocks += p.getStatistic(Statistic.MINE_BLOCK, m);

			}

			playerStatistics.put(p.getName(), blocks);

		}

		LeaderboardCommand.sendStatistics(sender, "Items Broken", playerStatistics);

		return false;
	}

}
