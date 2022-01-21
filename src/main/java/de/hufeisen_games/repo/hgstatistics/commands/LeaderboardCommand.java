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

import main.java.de.hufeisen_games.repo.hgstatistics.Messages;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.Jump;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.Bells;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.BlocksWalked;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.Deaths;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.Kills;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.NoteBlocksInteract;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.OnlineTime;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;

public class LeaderboardCommand implements CommandExecutor, TabCompleter {

	private HashMap<String, SubCommand> subCommands;

	public LeaderboardCommand() {

		subCommands = new HashMap<String, SubCommand>();

		addSubCommand("onlinetime", new OnlineTime());
		addSubCommand("blockswalked", new BlocksWalked());
		addSubCommand("noteblocks", new NoteBlocksInteract());
		addSubCommand("kills", new Kills());
		addSubCommand("deaths", new Deaths());
		addSubCommand("jump", new Jump());
		addSubCommand("bells", new Bells());
	}

	private void addSubCommand(String name, SubCommand subCommand) {

		subCommands.put(name, subCommand);

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length != 1) {

		} else {

			if (!executeSubCommand(args[0], sender, command, label, args)) {
				sender.sendMessage(Messages.SUB_COMMAND_NOT_FOUND);
			}

		}

		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

		if (args.length == 1) {
			return new ArrayList<String>(subCommands.keySet());
		}

		return null;

	}

	private boolean executeSubCommand(String name, CommandSender sender, Command command, String label, String[] args) {

		if (subCommands.get(name) != null) {

			subCommands.get(name).onCommand(sender, command, label, args);

			return true;

		} else {

			return false;

		}

	}

	public static void sendStatistics(CommandSender sender, String leaderBoardName, HashMap<String, Integer> unsortedMap) {
		
		sender.sendMessage("------------- Leaderboard ("+leaderBoardName+") -------------");
		
		int place = unsortedMap.size()+1;
		
		HashMap<String, Integer> playerStatistics = LeaderboardCommand.sortByValue(unsortedMap);
		
		for(String name : playerStatistics.keySet()) {

			place--;
			sender.sendMessage("Place "+place+" | "+name + ": " + playerStatistics.get(name));
		
		}
		
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
