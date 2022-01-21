package main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import main.java.de.hufeisen_games.repo.hgstatistics.commands.LeaderboardCommand;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;

public class OnlineTime implements SubCommand{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		HashMap<String, Integer> unsortedMap = new HashMap<String, Integer>();
		
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			
			unsortedMap.put(p.getName(), p.getStatistic(Statistic.TOTAL_WORLD_TIME));
			
		}
		
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("§7--------- §6§lLeaderboard §6(Online Time) §7---------");
		sender.sendMessage("");
		
		int place = unsortedMap.size()+1;
		
		HashMap<String, Integer> playerStatistics = LeaderboardCommand.sortByValue(unsortedMap);
		
		for(String name : playerStatistics.keySet()) {

			place--;
			sender.sendMessage("§ePlace §l"+place+" §7| §a"+name + "§7: §c" + playerStatistics.get(name)/20/60/60+" hours §o("+ playerStatistics.get(name)/20/60%60+" minutes)");
		}
		
		sender.sendMessage("");
		sender.sendMessage("§7--------- §6§lLeaderboard §6(Online Time) §7---------");
		sender.sendMessage("");
		
		return false;
	}

}
