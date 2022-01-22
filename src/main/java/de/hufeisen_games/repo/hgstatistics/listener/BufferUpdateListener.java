package main.java.de.hufeisen_games.repo.hgstatistics.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import main.java.de.hufeisen_games.repo.hgstatistics.HGStatistics;
import main.java.de.hufeisen_games.repo.hgstatistics.StatisticBuffer;

public class BufferUpdateListener implements Listener {

	public int currentAmount = 0;
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (!StatisticBuffer.isLoading) {
			if (e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
				StatisticBuffer.addToCache(Statistic.MINE_BLOCK, e.getPlayer().getName(), 1);
			}
		}

	}

	@EventHandler
	public void onItemCrafted(CraftItemEvent e) {
		if (!StatisticBuffer.isLoading) {
			Material craftedItemType = e.getCurrentItem().getType();
			
			for(ItemStack s : e.getInventory().getContents()) {
				if(s.getType() == craftedItemType) {
					
					currentAmount += s.getAmount();
					
				}
			}
			
			System.out.println(currentAmount);
			
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
					int newAmount = 0;
					
					for(ItemStack s : e.getWhoClicked().getInventory().getContents()) {
						if(s != null) {
							System.out.println(s.getType());
							if(s.getType() == craftedItemType) {
								
								newAmount += s.getAmount();
								
							}
						}
					}
					System.out.println(craftedItemType);
					System.out.println();
					System.out.println(newAmount);
					StatisticBuffer.addToCache(Statistic.CRAFT_ITEM, e.getWhoClicked().getName(), newAmount-currentAmount);
					
				}
			}.runTaskLaterAsynchronously(HGStatistics.getPlugin(), 20);

		}
	}

	@EventHandler
	public void onItemBreak(PlayerItemBreakEvent e) {
		if (!StatisticBuffer.isLoading) {
			StatisticBuffer.addToCache(Statistic.BREAK_ITEM, e.getPlayer().getName(), 1);
		}
	}

	@EventHandler
	public void onEntityKill(EntityDeathEvent e) {
		if (!StatisticBuffer.isLoading) {
			if (e.getEntity().getKiller() != null) {
				if (e.getEntity().getKiller().getType() == EntityType.PLAYER) {
					StatisticBuffer.addToCache(Statistic.KILL_ENTITY, e.getEntity().getKiller().getName(), 1);
				}
			}
		}
	}

}
