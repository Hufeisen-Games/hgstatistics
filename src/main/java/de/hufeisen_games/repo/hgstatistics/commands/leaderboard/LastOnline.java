package main.java.de.hufeisen_games.repo.hgstatistics.commands.leaderboard;

import main.java.de.hufeisen_games.repo.hgstatistics.HGStatistics;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.LeaderboardCommand;
import main.java.de.hufeisen_games.repo.hgstatistics.commands.type.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LastOnline implements SubCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration data = YamlConfiguration.loadConfiguration(new File(HGStatistics.getPlugin().getDataFolder() + "/data.yml"));
        HashMap<String, Long> unsortedMap = new HashMap<>();
        List<String> unknownPlayers = new ArrayList<>();

        for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
            if (p.isOnline()) continue;
            if (data.contains(p.getUniqueId().toString())) {

                Instant lastOnline = Instant.ofEpochSecond(data.getLong(p.getUniqueId() + ".lastOnline"));
                Instant now = Instant.now();

                long diff = now.getEpochSecond() - lastOnline.getEpochSecond();

                unsortedMap.put(p.getName(), diff);
            } else {
                unknownPlayers.add(p.getName());
            }
        }

        sender.sendMessage("");
        sender.sendMessage("");
        sender.sendMessage("");
        sender.sendMessage("§7--------- §6§lLeaderboard §6(Last Seen) §7---------");
        sender.sendMessage("");

        int place = unsortedMap.size() + unknownPlayers.size() + Bukkit.getOnlinePlayers().size() + 1;

        HashMap<String, Long> playerStatistics = LeaderboardCommand.sortByLongValue(unsortedMap);

        for (String name : unknownPlayers) {
            place--;
            sender.sendMessage("§ePlace §l" + place + " §7| §a" + name + "§7: §7 UNKNOWN");
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            place--;
            sender.sendMessage("§ePlace §l" + place + " §7| §a" + p.getName() + "§7: §c NOW ONLINE");
        }

        for (String name : playerStatistics.keySet()) {
            place--;
            sender.sendMessage("§ePlace §l" + place + " §7| §a" + name + "§7: §c" + playerStatistics.get(name) / 60 / 60 / 24 + " days, " + playerStatistics.get(name) / 60 / 60 % 24 + " hours §o(" + playerStatistics.get(name) / 60 % 60 + " minutes) ago");
        }

        sender.sendMessage("");
        sender.sendMessage("§7--------- §6§lLeaderboard §6(Last Seen) §7---------");
        sender.sendMessage("");

        return false;
    }

}
