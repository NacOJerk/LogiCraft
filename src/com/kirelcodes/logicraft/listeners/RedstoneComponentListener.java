package com.kirelcodes.logicraft.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.plugin.Plugin;

public class RedstoneComponentListener implements Listener {
	public RedstoneComponentListener(Plugin p) {
		Bukkit.getPluginManager().registerEvents(this, p);
	}

	@EventHandler
	public void blockUpdate(BlockPhysicsEvent e) {
		RedstoneUpdateContainer.addEvent(e);
	}

}
