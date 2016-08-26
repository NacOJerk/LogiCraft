package com.kirelcodes.logicraft.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.kirelcodes.logicraft.LogiCraft;

public class RedstoneUpdateContainer extends BukkitRunnable {
	private static HashMap<Location, List<BlockPhysicsEvent>> eventContainer;

	public RedstoneUpdateContainer() {
		eventContainer = new HashMap<>();
		this.runTaskTimer(LogiCraft.getInstance(), 0L, 1L);
	}

	public static void clear() {
		eventContainer.clear();
	}

	public static void addEvent(BlockPhysicsEvent e) {
		if (!eventContainer.containsKey(e.getBlock().getLocation()))
			eventContainer.put(e.getBlock().getLocation(), new ArrayList<>());
		if (eventContainer.get(e.getBlock().getLocation()) == null) {
			eventContainer.remove(e.getBlock().getLocation());
			eventContainer.put(e.getBlock().getLocation(), new ArrayList<>());
		}
		try {
			if (e == null || e.getBlock() == null
					|| e.getBlock().getLocation() == null
					|| eventContainer == null
					|| eventContainer.get(e.getBlock().getLocation()) == null)
				return;
			eventContainer.get(e.getBlock().getLocation()).add(e);
		} catch (Exception e1) {
			e.getBlock().getState().update(false);
			return;
		}
	}

	public static BlockPhysicsEvent getLastEvent(Location loc) {
		if (!eventContainer.containsKey(loc))
			return null;
		if (eventContainer.get(loc).isEmpty())
			return null;
		List<BlockPhysicsEvent> list = eventContainer.get(loc);
		return list.get(list.size() - 1);
	}

	public static List<Location> getLocations() {
		if (eventContainer.isEmpty())
			return new ArrayList<>();
		return new ArrayList<>(eventContainer.keySet());
	}

	@Override
	public void run() {
		for (Location loc : getLocations()) {
			new BlockUpdate(getLastEvent(loc));
		}
		clear();
	}

}
