package com.kirelcodes.logicraft.components.gates;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.material.Lever;

public class Bridge extends Gate{
	public Bridge(Location loc) {
		super(loc, Material.ENDER_STONE, "BRIDGE");
	}
	
	public Bridge(ArmorStand armor) {
		super(armor);
	}

	@Override
	public void redstoneUpdate() {
		if (checkForConnection(getLocation()) == 0) {
			turnLever(getLocation(), false);
			return;
		}
		List<BlockFace> directions = getBridgeDirections(getLocation());
		if (directions.size() == 0) {
			turnLever(getLocation(), false);
			return;
		}
		turnOnBlockFace(getLocation(), true, directions);
	}
	@SuppressWarnings("deprecation")
	private void turnOnBlockFace(Location loc, boolean onOf,
			List<BlockFace> faces) {
		Block block = loc.getBlock();
		if (onOf) {
			for (BlockFace face : faces) {
				Block b = block.getRelative(face);
				if (b.getType() != Material.LEVER)
					continue;
				Lever lever = new Lever(Material.LEVER, b.getData());
				if (lever.isPowered())
					continue;
				lever.setPowered(true);
				b.setData(lever.getData());
				b.getState().update(true);

			}
		}
		ArrayList<BlockFace> remove = new ArrayList<>();
		BlockFace[] goodFaces = { BlockFace.DOWN, BlockFace.EAST,
				BlockFace.SOUTH, BlockFace.NORTH, BlockFace.WEST, BlockFace.UP };
		for (BlockFace bf : goodFaces) {
			if (faces.contains(bf))
				continue;
			remove.add(bf);
		}
		for (BlockFace face : remove) {
			Block b = block.getRelative(face);
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

	@SuppressWarnings("deprecation")
	private List<BlockFace> getBridgeDirections(Location loc) {
		Block block = loc.getBlock();
		BlockFace[] goodFaces = { BlockFace.DOWN, BlockFace.EAST,
				BlockFace.SOUTH, BlockFace.NORTH, BlockFace.WEST, BlockFace.UP };
		List<BlockFace> bridge = new ArrayList<>();
		for (BlockFace face : goodFaces) {
			Block b = block.getRelative(face);
			if (b.getType() != Material.REDSTONE_WIRE)
				continue;
			if (b.getData() == 0)
				continue;
			switch (face) {
			case DOWN:
				bridge.add(BlockFace.UP);
				break;
			case UP:
				bridge.add(BlockFace.DOWN);
				break;
			case NORTH:
				bridge.add(BlockFace.SOUTH);
				break;
			case SOUTH:
				bridge.add(BlockFace.NORTH);
				break;
			case WEST:
				bridge.add(BlockFace.EAST);
				break;
			case EAST:
				bridge.add(BlockFace.WEST);
				break;
			default:
				break;
			}

		}
		return bridge;
	}

}
