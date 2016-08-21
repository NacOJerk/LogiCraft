package com.kirelcodes.logicraft.components.gates;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;

public class OR extends Gate {

	public OR(Location loc) {
		super(loc, Material.GLASS, "OR");
	}
	public OR(ArmorStand armor) {
		super(armor);
	}

	@Override
	public void redstoneUpdate() {
		if(checkForConnection(getLocation()) == 0){
			turnLever(getLocation(), false);
			return;
		}
		turnLever(getLocation(), true);
	}

}
