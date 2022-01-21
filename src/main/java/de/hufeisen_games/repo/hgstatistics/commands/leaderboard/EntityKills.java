package main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

import main.java.de.hufeisen_games.repo.hgstatistics.Messages;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.LeaderboardCommand;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;

public class EntityKills implements SubCommand{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		HashMap<String, Integer> playerStatistics = new HashMap<>();
		
		sender.sendMessage(Messages.LAGG_WARNING);
		
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			
			int entitys = 0;
			
			for(EntityType e : EntityType.values()) {
				
				if(e != EntityType.UNKNOWN) {
					entitys += p.getStatistic(Statistic.KILL_ENTITY, e);
				}
				
			}
			
			playerStatistics.put(p.getName(), entitys);
			
		}
		
		LeaderboardCommand.sendStatistics(sender, "Entity Kills", playerStatistics);
		
		return false;
	}

}

