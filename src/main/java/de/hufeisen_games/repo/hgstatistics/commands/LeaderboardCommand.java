package main.java.de.hufeisen_games.repo.hgstatistics.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.scheduler.BukkitRunnable;

import main.java.de.hufeisen_games.repo.hgstatistics.HGStatistics;
import main.java.de.hufeisen_games.repo.hgstatistics.Messages;
import main.java.de.hufeisen_games.repo.hgstatistics.StatisticBuffer;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.Bells;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.BlocksWalked;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.BrokenBlocks;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.CraftItem;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.Deaths;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.EntityKills;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.HelpCommand;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.ItemEnchant;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.Itembreak;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.Jump;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.Kills;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.NoteBlocks;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.OnlineTime;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;

public class LeaderboardCommand implements CommandExecutor, TabCompleter {

	public static HashMap<String, SubCommand> subCommands;

	public LeaderboardCommand() {

		subCommands = new HashMap<String, SubCommand>();

		addSubCommand("bells", new Bells());
		addSubCommand("blockswalked", new BlocksWalked());
		addSubCommand("blocksbroken", new BrokenBlocks());
		addSubCommand("craftitem", new CraftItem());
		addSubCommand("deaths", new Deaths());
		addSubCommand("entitykills", new EntityKills());
		addSubCommand("itembreak", new Itembreak());
		addSubCommand("itemenchant", new ItemEnchant());
		addSubCommand("jump", new Jump());
		addSubCommand("kills", new Kills());
		addSubCommand("noteblocks", new NoteBlocks());
		addSubCommand("onlinetime", new OnlineTime());
		
		addSubCommand("help", new HelpCommand());
	}

	private void addSubCommand(String name, SubCommand subCommand) {

		subCommands.put(name, subCommand);

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(sender.hasPermission("hgstatistics.leaderboard")) {
		
			if (args.length != 1) {
				
				executeSubCommand("help", sender, command, label, args);
				
			} else {
				if (!executeSubCommand(args[0], sender, command, label, args)) {
					sender.sendMessage(Messages.SUB_COMMAND_NOT_FOUND);
				} else {
					if(StatisticBuffer.isLoading) {
						sender.sendMessage(Messages.BUFFER_IS_LOADING);
					}
				}
	
			}
			
		} else {
			
			sender.sendMessage(Messages.NO_PERMISSIONS);
			
		}

		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

		if(sender.hasPermission("hgstatistics.leaderboard")) {
			if (args.length == 1) {
				
				List<String> tab = new ArrayList<String>();
				
				for(String name : subCommands.keySet()) {
					
					if(sender.hasPermission("hgstatistics.leaderboard."+name) || sender.hasPermission("hgstatistics.leaderboard.all") || name.equalsIgnoreCase("help")) {
						tab.add(name);
					}
					
				}
				
				return tab;
			}
		}

		return new ArrayList<String>();

	}

	private boolean executeSubCommand(String name, CommandSender sender, Command command, String label, String[] args) {

		if (subCommands.get(name.toLowerCase()) != null) {
			
			if(sender.hasPermission("hgstatistics.leaderboard."+name) || sender.hasPermission("hgstatistics.leaderboard.all") || name.equalsIgnoreCase("help")) {
				
				new BukkitRunnable() {
					
					@Override
					public void run() {
						
						subCommands.get(name.toLowerCase()).onCommand(sender, command, label, args);
						
					}
				}.runTaskAsynchronously(HGStatistics.getPlugin());
			} else {
				sender.sendMessage(Messages.NO_PERMISSIONS);
			}

			return true;

		} else {

			return false;

		}

	}

	public static void sendStatistics(CommandSender sender, String leaderBoardName, HashMap<String, Integer> unsortedMap) {
		
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("§7--------- §6§lLeaderboard §6("+leaderBoardName+") §7---------");
		sender.sendMessage("");
		int place = unsortedMap.size()+1;
		
		HashMap<String, Integer> playerStatistics = LeaderboardCommand.sortByValue(unsortedMap);
		
		for(String name : playerStatistics.keySet()) {

			place--;
			sender.sendMessage("§ePlace §l"+place+" §7| §a"+name + "§7: §c" + playerStatistics.get(name));
		
		}
		sender.sendMessage("");
		sender.sendMessage("§7--------- §6§lLeaderboard §6("+leaderBoardName+") §7---------");
		sender.sendMessage("");
	}
	
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> unsortedMap) {
		
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortedMap.entrySet());
		
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					return o1.getValue().compareTo(o2.getValue());
			}
			
		});
		
		HashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		
		for (Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		return sortedMap;
	}
}
