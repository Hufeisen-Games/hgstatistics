package main.java.de.hufeisen_games.repo.online_time.commands;

import java.util.TreeMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ShowWalkedBlocks implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		TreeMap<Integer, String> playerTime = new TreeMap<Integer, String>();
		
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			
			playerTime.put(p.getStatistic(Statistic.WALK_ONE_CM), p.getName());
			
		}
		
		int place = playerTime.size()+1;
		
		for(String name : playerTime.values()) {
			for(Entry<Integer, String> entry: playerTime.entrySet()) {
	
			      
				if(entry.getValue() == name) {
					place--;
					sender.sendMessage("Platz "+place+" | "+name + ": " + entry.getKey()+" Blöcke ("+ entry.getKey()/20/60%60+") Minuten");
					break;
				}
			}
		}
		
		return false;
	}
}
