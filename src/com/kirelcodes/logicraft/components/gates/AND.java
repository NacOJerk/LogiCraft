package com.kirelcodes.logicraft.components.gates;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;

public class AND extends Gate{

	public AND(Location loc){
		super(loc , Material.COAL_BLOCK , "AND");
		setNumberTransistors(2);
	}
	
	public AND(ArmorStand armor) {
		super(armor);
		setNumberTransistors(2);
	}

	@Override
	public void redstoneUpdate() {
		if(checkForConnection(getLocation()) >= 2){
			turnLever(getLocation(), true);
			return;
		}
		turnLever(getLocation(), false);
	}

}
