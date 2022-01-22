package main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import main.java.de.hufeisen_games.repo.hgstatistics.HGStatistics;
import main.java.de.hufeisen_games.repo.hgstatistics.Messages;
import main.java.de.hufeisen_games.repo.hgstatistics.StatisticBuffer;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;

public class UpdateCache implements SubCommand{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender.hasPermission("hgstatistics.updateleaderboard")) {
			
			sender.sendMessage(Messages.CACHE_UPDATE);
			long time = System.currentTimeMillis();  
			StatisticBuffer.loadIntoCache();
			
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
					if(!StatisticBuffer.isLoading) {
						
						sender.sendMessage("§a§lCache Reloaded! §7(§lTime: §6"+((System.currentTimeMillis() - time)/1000)+"."+((System.currentTimeMillis() - time)%1000)+"s§7)");
						this.cancel();
					}
					
					
				}
			}.runTaskTimerAsynchronously(HGStatistics.getPlugin(), 0, 20);
			
		} else {
			sender.sendMessage(Messages.NO_PERMISSIONS);
		}
		
		return false;
	}

}
