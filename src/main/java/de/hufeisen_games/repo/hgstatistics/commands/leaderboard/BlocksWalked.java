package main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard;

import main.java.de.hufeisen_games.repo.hgstatistics.commands.LeaderboardCommand;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class BlocksWalked implements SubCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        HashMap<String, Integer> playerStatistics = new HashMap<>();

        for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {

            playerStatistics.put(p.getName(), p.getStatistic(Statistic.WALK_ONE_CM) / 100);

        }

        LeaderboardCommand.sendStatistics(sender, "Blocks Walked", playerStatistics);

        return false;
    }
}
