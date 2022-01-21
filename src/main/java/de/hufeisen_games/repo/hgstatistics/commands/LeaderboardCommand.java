package main.java.de.hufeisen_games.repo.hgstatistics.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.Jump;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.Bells;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.BlocksWalked;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.Deaths;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.Kills;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.NoteBlocksInteract;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard.OnlineTime;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;

public class LeaderboardCommand implements CommandExecutor, TabCompleter{

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
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(args.length != 1) {
			
			
			
		} else {
			
			if(!executeSubCommand(args[0], sender, command, label, args)) {
				sender.sendMessage("�cDer angegebene Sub Befehl existiert nicht. Eine Liste aller Sub Commands kannst du dir mit �a/leaderboard help �canzeigen lassen.");
			}
			
		}
		
		return false;
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

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		
		return new ArrayList<String>(subCommands.keySet());
		
	}
}
