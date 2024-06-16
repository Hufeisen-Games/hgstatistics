package main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard;

import main.java.de.hufeisen_games.repo.hgstatistics.StatisticBuffer;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.LeaderboardCommand;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class BrokenItems implements SubCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        HashMap<String, Integer> playerStatistics = StatisticBuffer.getStatisticFromCache(Statistic.BREAK_ITEM);

        LeaderboardCommand.sendStatistics(sender, "Items Broken", playerStatistics);

        return false;
    }

}
