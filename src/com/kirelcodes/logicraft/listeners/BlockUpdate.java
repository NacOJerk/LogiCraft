package com.kirelcodes.logicraft.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.kirelcodes.logicraft.LogiCraft;
import com.kirelcodes.logicraft.components.Component;

public class BlockUpdate extends BukkitRunnable {

	private BlockPhysicsEvent e;

	
	public BlockUpdate(BlockPhysicsEvent e) {
		this.e = e;
		this.runTask(LogiCraft.getInstance());
	}

	public static List<Entity> getNearByEntities(Location loc, double distance) {
		Chunk c = loc.getChunk();
		List<Entity> entites = new ArrayList<>();
		if(c.getEntities() == null || c.getEntities().length == 0)
			return new ArrayList<>();
		for (Entity e : c.getEntities()) {
			if (e.getLocation().distanceSquared(loc) <= (distance * distance)) {
				entites.add(e);
			}
		}
		return entites;
	}

	
	@Override
	public void run() {
		List<Material> redstone = new ArrayList<>();
		redstone.add(Material.DIODE_BLOCK_OFF);
		redstone.add(Material.DIODE_BLOCK_ON);
		redstone.add(Material.LEVER);
		if (!(redstone.contains(e.getChangedType()) || e.getChangedType()
				.toString().startsWith("REDSTONE")))
			return;
		BlockFace[] goodFaces = { BlockFace.DOWN, BlockFace.EAST,
				BlockFace.SOUTH, BlockFace.NORTH, BlockFace.WEST, BlockFace.UP };
		for (BlockFace face : goodFaces) {
			Block b = e.getBlock().getRelative(face);
			if (b.getType() != Material.AIR)
				continue;
			for (Entity en : Component.getNearByEntities(b.getLocation().add(0.5, 0.5, 0.5), 0.5)) {
				if (!(en instanceof ArmorStand))
					continue;
				new BlockUpdateHandleComponent((ArmorStand) en);
			}
		}
	}

}
