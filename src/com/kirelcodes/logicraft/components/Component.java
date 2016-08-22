package com.kirelcodes.logicraft.components;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Lever;

import com.kirelcodes.logicraft.utils.NBTRW;

public abstract class Component {
	private ArmorStand component;
	private int numberTransistors;
	public Component(Location loc) {
		Location loc2 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		component = (ArmorStand) loc.getWorld().spawnEntity(loc2.clone().add(0.5, 0, 0.5),
				EntityType.ARMOR_STAND);
		component.setVisible(false);
		component.setSmall(true);
		component.setMarker(true);
		component.setGravity(false);
		ItemStack dataSaver = null;
		ItemStack data = new ItemStack(Material.BARRIER);
		try {
			dataSaver = NBTRW.writeNBT(data,
					"componentType", this.getClass().getName());
		} catch (Exception e) {
			e.printStackTrace();
			getComponent().remove();
			return;
		}
		getComponent().setChestplate(dataSaver);
	}
	public Component(ArmorStand armor){
		if (armor.getChestplate() == null
				|| armor.getChestplate().getType() != Material.BARRIER)
			return;
		this.component = armor;
	}
	public void spawn(Location loc) {
	}

	public static Component getComponent(ArmorStand armor) {
		if (armor.getChestplate() == null
				|| armor.getChestplate().getType() != Material.BARRIER)
			return null;
		ItemStack dataContainer = armor.getChestplate();
		String classPath = "";
		try {
			if (!NBTRW.containsNBTTag(dataContainer, "componentType"))
				return null;
			classPath = NBTRW.getNBTString(dataContainer, "componentType");
			@SuppressWarnings("unchecked")
			Class<? extends Component> classCom = (Class<? extends Component>) Class.forName(classPath);
			return classCom.getConstructor(ArmorStand.class).newInstance(armor);
		} catch (Exception e) {
			return null;
		}
	}

	public ArmorStand getComponent() {
		return component;
	}

	public Location getLocation(){
		return getComponent().getLocation();
	}
	
	public ItemStack getDataSaver() {
		return getComponent().getChestplate();
	}
	/**
	 * Called whenever a redstone update happends near it
	 */
	public abstract void redstoneUpdate();
	
	
	
	public int getNumberTransistors() {
		return numberTransistors;
	}
	protected void setNumberTransistors(int numberTransistors) {
		this.numberTransistors = numberTransistors;
	}
	/**
	 * Returns the amount of redstone wires (That are turned on ) connected to the component
	 * @param loc
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int checkForConnection(Location loc) {
		Block block = loc.getBlock();
		BlockFace[] goodFaces = { BlockFace.DOWN, BlockFace.EAST,
				BlockFace.SOUTH, BlockFace.NORTH, BlockFace.WEST, BlockFace.UP };
		int power = 0;
		for (BlockFace face : goodFaces) {
			Block b = block.getRelative(face);
			if (b.getType() != Material.REDSTONE_WIRE)
				continue;
			if (b.getData() > 0)
				power++;
		}
		return power;
	}
	/**
	 * Returns the amount of redstone repeaters (That are turned on ) connected to the component
	 * @param loc
	 * @return
	 */
	public static int checkForConnectionRepeater(Location loc) {
		Block block = loc.getBlock();
		BlockFace[] goodFaces = { BlockFace.DOWN, BlockFace.EAST,
				BlockFace.SOUTH, BlockFace.NORTH, BlockFace.WEST, BlockFace.UP };
		int power = 0;
		for (BlockFace face : goodFaces) {
			Block b = block.getRelative(face);
			if (b.getType() != Material.DIODE_BLOCK_ON)
				continue;
			power++;
		}
		return power;
	}
	/**
	 * Turns on/of near bye levers
	 * @param loc
	 * @param onOf
	 */
	@SuppressWarnings("deprecation")
	public static void turnLever(Location loc, boolean onOf) {
		Block block = loc.getBlock();
		BlockFace[] goodFaces = { BlockFace.DOWN, BlockFace.EAST,
				BlockFace.SOUTH, BlockFace.NORTH, BlockFace.WEST, BlockFace.UP };
		for (BlockFace face : goodFaces) {
			Block b = block.getRelative(face);
			if (onOf) {
				if (b.getType() != Material.LEVER)
					continue;
				Lever lever = new Lever(Material.LEVER, b.getData());
				if (lever.isPowered())
					continue;
				lever.setPowered(true);
				b.setData(lever.getData());
				b.getState().update(true);
			} else {
				if (b.getType() != Material.LEVER)
					continue;
				Lever lever = new Lever(Material.LEVER, b.getData());
				if (!lever.isPowered())
					continue;
				lever.setPowered(false);
				b.setData(lever.getData());
				b.getState().update(true);
			}
		}
	}
	
	public List<Entity> getNearByEntities(Location loc, double distance) {
		Chunk c = loc.getChunk();
		List<Entity> entites = new ArrayList<>();
		for (Entity e : c.getEntities()) {
			if (e.getLocation().distanceSquared(loc) <= (distance * distance)) {
				entites.add(e);
			}
		}
		return entites;
	}


}
