package main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard;

import java.util.TreeMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;

public class Jump implements SubCommand{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		sender.sendMessage("------------- Leaderboard (Jump) -------------");
		
		TreeMap<Integer, String> playerTime = new TreeMap<Integer, String>();
		
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			
			playerTime.put(p.getStatistic(Statistic.JUMP), p.getName());
			
		}
		
		int place = playerTime.size()+1;
		
		for(String name : playerTime.values()) {
			for(Entry<Integer, String> entry: playerTime.entrySet()) {
	
			      
				if(entry.getValue() == name) {
					place--;
					sender.sendMessage("Platz "+place+" | "+name + ": " + entry.getKey());
					break;
				}
			}
		}
		
		sender.sendMessage("");
		
		return false;
	}

}