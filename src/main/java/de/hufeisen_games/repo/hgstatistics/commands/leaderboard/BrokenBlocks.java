package main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard;

import java.util.HashMap;

import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import main.java.de.hufeisen_games.repo.hgstatistics.StatisticBuffer;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.LeaderboardCommand;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;

public class BrokenBlocks implements SubCommand {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		HashMap<String, Integer> playerStatistics = StatisticBuffer.getStatisticFromCache(Statistic.MINE_BLOCK);

		LeaderboardCommand.sendStatistics(sender, "Blocks Broken", playerStatistics);

		return false;
	}

}
