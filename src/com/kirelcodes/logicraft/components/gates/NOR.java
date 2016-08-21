package com.kirelcodes.logicraft.components.gates;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;

public class NOR extends Gate {
	public NOR(Location loc) {
		super(loc, Material.ICE, "NOR");
	}

	public NOR(ArmorStand armor) {
		super(armor);
	}

	@Override
	public void redstoneUpdate() {
		if(checkForConnection(getLocation()) == 0){
			turnLever(getLocation(), true);
			return;
		}
		turnLever(getLocation(), false);
	}

}
