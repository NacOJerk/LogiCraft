package com.kirelcodes.logicraft.listeners;

import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

import com.kirelcodes.logicraft.LogiCraft;
import com.kirelcodes.logicraft.components.Component;

public class BlockUpdateHandleComponent extends BukkitRunnable{
	
	private ArmorStand armorStand;
	
	public BlockUpdateHandleComponent(ArmorStand armorStand) {
		this.armorStand = armorStand;
		this.runTask(LogiCraft.getInstance());
	}
	
	@Override	
	public void run() {
		Component comp = null;
		if ((comp = Component.getComponent(armorStand)) == null)
			return;
		comp.redstoneUpdate();
/*		new BukkitRunnable() {
			
			@Override
			public void run() {
			}
		}.runTaskAsynchronously(LogiCraft.getInstance());
*/	}

}
