package main.java.de.hufeisen_games.repo.hgstatistics.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import main.java.de.hufeisen_games.repo.hgstatistics.Messages;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.BlocksWalked;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.OnlineTime;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;

public class LeaderboardCommand implements CommandExecutor, TabCompleter{

	private HashMap<String, SubCommand> subCommands;
	
	public LeaderboardCommand() {
		
		subCommands = new HashMap<String, SubCommand>();
		
		addSubCommand("onlinetime", new OnlineTime());
		addSubCommand("blockswalked", new BlocksWalked());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(args.length != 1) {
			
			
			
		} else {
			
			if(!executeSubCommand(args[0], sender, command, label, args)) {
				sender.sendMessage(Messages.SUB_COMMAND_NOT_FOUND);
			}
			
		}
		
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		
		if(args.length == 0) {
			return new ArrayList<String>(subCommands.keySet());
		}
		
		return null;
		
	}
	
	private void addSubCommand(String name, SubCommand subCommand) {
		
		subCommands.put(name, subCommand);
		
	}
	
	private boolean executeSubCommand(String name, CommandSender sender, Command command, String label, String[] args) {
		
		if(subCommands.get(name) != null) {
			
			subCommands.get(name).onCommand(sender, command, label, args);
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
}
