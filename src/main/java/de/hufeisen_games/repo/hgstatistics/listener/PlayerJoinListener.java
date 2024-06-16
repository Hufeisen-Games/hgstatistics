package main.java.de.hufeisen_games.repo.hgstatistics.listener;

import main.java.de.hufeisen_games.repo.hgstatistics.HGStatistics;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        String version = getVersion();

        if (version != null) {

            if (!version.equals(HGStatistics.getPlugin().getDescription().getVersion())) {

                if (e.getPlayer().hasPermission("hgstatistics.updateNotification")) {

                    e.getPlayer().sendMessage("");
                    e.getPlayer().sendMessage("§7--------- §6§lHGStatistics §6§oV. "
                            + HGStatistics.getPlugin().getDescription().getVersion() + " §6(Update) §7---------");
                    e.getPlayer().sendMessage("");

                    TextComponent updateMessage = new TextComponent("§a§lThere is an update available (version §6§l" + version + "§a§l). Press ");
                    TextComponent updateLink = new TextComponent("§c§nhere");

                    updateLink.setClickEvent(new ClickEvent(Action.OPEN_URL, "https://codeinfo.hufeisen-games.de/hgstatistics/download/latest"));

                    updateMessage.addExtra(updateLink);
                    updateMessage.addExtra(" §a§lto download!");

                    e.getPlayer().spigot().sendMessage(updateMessage);
                    e.getPlayer().sendMessage("");
                    e.getPlayer().sendMessage("§6New Features:");
                    for (String s : getUpdateLog()) {
                        e.getPlayer().sendMessage(" §7- §e" + s);
                    }
                    e.getPlayer().sendMessage("");
                    e.getPlayer().sendMessage("§7--------- §6§lHGStatistics §6§oV. "
                            + HGStatistics.getPlugin().getDescription().getVersion() + " §6(Update) §7---------");
                    e.getPlayer().sendMessage("");

                }

            }

        }

    }

    private String getVersion() {
        URL url;
        try {
            url = new URL("https://codeinfo.hufeisen-games.de/hgstatistics/version.hgi");

            return getUrlBody(url);
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, "Could not check for updates!", e);
        }

        return null;
    }

    private String getUrlBody(URL url) throws IOException {
        URLConnection urlConn = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();
        return a.toString();
    }

    private String[] getUpdateLog() {
        URL url;
        try {
            url = new URL("https://codeinfo.hufeisen-games.de/hgstatistics/updatelog.hgi");

            getUrlBody(url);

            return getUrlBody(url).split(";");
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, "Could not get latest update log!", e);
        }

        return null;
    }

}
