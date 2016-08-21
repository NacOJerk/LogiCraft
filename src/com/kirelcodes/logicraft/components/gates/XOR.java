package com.kirelcodes.logicraft.components.gates;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;

public class XOR extends Gate {
	public XOR(Location loc) {
		super(loc, Material.REDSTONE_BLOCK, "XOR");
	}
	public XOR(ArmorStand armor) {
		super(armor);
		
	}

	@Override
	public void redstoneUpdate() {
		if(checkForConnection(getLocation()) == 1){
			turnLever(getLocation(), true);
			return;
		}
		turnLever(getLocation(), false);
	}
}
