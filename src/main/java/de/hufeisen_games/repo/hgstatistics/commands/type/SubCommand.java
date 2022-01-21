package main.java.de.hufeisen_games.repo.hgstatistics.commands.type;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface SubCommand {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args);
	
}

