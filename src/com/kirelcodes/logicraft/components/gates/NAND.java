package com.kirelcodes.logicraft.components.gates;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;

public class NAND extends Gate{

	public NAND(Location loc) {
		super(loc, Material.GLOWSTONE, "NAND");
	}
	public NAND(ArmorStand armor) {
		super(armor);
	}
	@Override
	public void redstoneUpdate() {
		if(checkForConnection(getLocation()) >= 2){
			turnLever(getLocation(), false);
			return;
		}
		turnLever(getLocation(), true);
	}

}
