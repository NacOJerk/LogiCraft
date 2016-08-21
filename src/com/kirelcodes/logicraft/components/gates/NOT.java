package com.kirelcodes.logicraft.components.gates;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;

public class NOT extends Gate{

	public NOT(Location loc){
		super(loc , Material.GLASS , "NOT");
		setNumberTransistors(1);
	}
	
	public NOT(ArmorStand armor) {
		super(armor);
		setNumberTransistors(1);
	}

	@Override
	public void redstoneUpdate() {
		if(checkForConnection(getComponent().getLocation()) >= 1){
			turnLever(getLocation(), false);
			return;
		}
		turnLever(getLocation(), true);
	}

}
