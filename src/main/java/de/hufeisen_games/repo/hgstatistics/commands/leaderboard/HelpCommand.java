package main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard;

import main.java.de.hufeisen_games.repo.hgstatistics.HGStatistics;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.LeaderboardCommand;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class HelpCommand implements SubCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage("");
        sender.sendMessage("§7--------- §6§lHGStatistics §6§oV. " + HGStatistics.getPlugin().getDescription().getVersion() + " §6(Leaderboard Help) §7---------");
        sender.sendMessage("");
        sender.sendMessage("§aDo §6§o/leaderboard <statistic> §ato display you a leaderboard with the given statistic!");
        sender.sendMessage("");
        sender.sendMessage("§c§lSub Commands:");
        sender.sendMessage(" §7- §e§ohelp");
        sender.sendMessage(" §7- §e§oreloadcache");
        sender.sendMessage(" §7- §e§oany statistic");
        sender.sendMessage("");
        sender.sendMessage("§c§lAll Statistics:");
        for (String subCommand : LeaderboardCommand.subCommands.keySet()) {

            if (!subCommand.equals("help") && !subCommand.equals("reloadcache")) {
                sender.sendMessage(" §7- §e§o" + subCommand);
            }

        }

        sender.sendMessage("");
        sender.sendMessage("§7--------- §6§lHGStatistics §6§oV. " + HGStatistics.getPlugin().getDescription().getVersion() + " §6(Leaderboard Help) §7---------");
        sender.sendMessage("");

        return false;
    }

}
