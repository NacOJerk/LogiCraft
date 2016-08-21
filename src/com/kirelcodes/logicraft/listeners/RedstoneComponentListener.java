package com.kirelcodes.logicraft.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.plugin.Plugin;

import com.kirelcodes.logicraft.components.Component;

public class RedstoneComponentListener implements Listener {
	public RedstoneComponentListener(Plugin p) {
		Bukkit.getPluginManager().registerEvents(this, p);
	}

	private List<Entity> getNearByEntities(Location loc, double distance) {
		Chunk c = loc.getChunk();
		List<Entity> entites = new ArrayList<>();
		for (Entity e : c.getEntities()) {
			if (e.getLocation().distanceSquared(loc) <= (distance * distance)) {
				entites.add(e);
			}
		}
		return entites;
	}

	@EventHandler
	public void blockUpdate(BlockPhysicsEvent e) {
		List<Material> redstone = new ArrayList<>();
		redstone.add(Material.DIODE_BLOCK_OFF);
		redstone.add(Material.DIODE_BLOCK_ON);
		if (!(redstone.contains(e.getChangedType()) || e.getChangedType()
				.toString().startsWith("REDSTONE")))
			return;
		BlockFace[] goodFaces = { BlockFace.DOWN, BlockFace.EAST,
				BlockFace.SOUTH, BlockFace.NORTH, BlockFace.WEST, BlockFace.UP };
		for (BlockFace face : goodFaces) {
			Block b = e.getBlock().getRelative(face);
			if (b.getType() != Material.AIR)
				continue;
			for (Entity en : getNearByEntities(b.getLocation(), 1)) {
				if (!(en instanceof ArmorStand))
					continue;
				Component comp = null;
				if ((comp = Component.getComponent((ArmorStand) en)) == null)
					continue;
				comp.redstoneUpdate();
			}
		}
	}

}
