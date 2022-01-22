package main.java.de.hufeisen_games.repo.hgstatistics.listener;

import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import main.java.de.hufeisen_games.repo.hgstatistics.StatisticBuffer;

public class BufferUpdateListener implements Listener{

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		
		StatisticBuffer.addToCache(Statistic.MINE_BLOCK, e.getPlayer().getName(), 1);
		
	}
	
}
