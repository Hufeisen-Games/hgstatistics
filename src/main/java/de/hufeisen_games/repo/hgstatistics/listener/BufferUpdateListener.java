package main.java.de.hufeisen_games.repo.hgstatistics.listener;

import org.bukkit.GameMode;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import main.java.de.hufeisen_games.repo.hgstatistics.StatisticBuffer;

public class BufferUpdateListener implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {

		if(e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
			StatisticBuffer.addToCache(Statistic.MINE_BLOCK, e.getPlayer().getName(), 1);
		}

	}

	@EventHandler
	public void onItemCrafted(CraftItemEvent e) {

		ItemStack craftedItem = e.getInventory().getResult();
		Inventory Inventory = e.getInventory();
		ClickType clickType = e.getClick();
		
		int realAmount = craftedItem.getAmount();
		
		if (clickType.isShiftClick()) {
			
			int lowerAmount = craftedItem.getMaxStackSize() + 1000;
			
			for (ItemStack actualItem : Inventory.getContents()) {
				
				if (!actualItem.getType().isAir() && lowerAmount > actualItem.getAmount()
						&& !actualItem.getType().equals(craftedItem.getType()))
					lowerAmount = actualItem.getAmount();
			}

			realAmount = lowerAmount * craftedItem.getAmount();
		}

		StatisticBuffer.addToCache(Statistic.CRAFT_ITEM, e.getWhoClicked().getName(), realAmount);

	}
	
	@EventHandler
	public void onItemBreak(PlayerItemBreakEvent e) {
		
		StatisticBuffer.addToCache(Statistic.BREAK_ITEM, e.getPlayer().getName(), 1);
		
	}
	
	@EventHandler
	public void onEntityKill(EntityDeathEvent e) {
		
		if(e.getEntity().getKiller().getType() == EntityType.PLAYER) {
			StatisticBuffer.addToCache(Statistic.KILL_ENTITY, e.getEntity().getKiller().getName(), 1);
		}
		
	}

}
