package main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import main.java.de.hufeisen_games.repo.hgstatistics.commands.LeaderboardCommand;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;

public class NoteBlocksInteract implements SubCommand{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		HashMap<String, Integer> playerStatistics = new HashMap<>();
		
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			
			playerStatistics.put(p.getName(), p.getStatistic(Statistic.NOTEBLOCK_PLAYED)+p.getStatistic(Statistic.NOTEBLOCK_TUNED));
			
		}
		
		LeaderboardCommand.sendStatistics(sender, "Note Blocks Interact", playerStatistics);
		
		return false;
	}

}
