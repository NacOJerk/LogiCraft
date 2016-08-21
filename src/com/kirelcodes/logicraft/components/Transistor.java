package com.kirelcodes.logicraft.components;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

public class Transistor extends Component {

	public Transistor(Location loc) {
		super(loc);
		getComponent().setHelmet(new ItemStack(Material.GOLD_BLOCK));
		getComponent().setCustomName("Transistor".toUpperCase());
		getComponent().setCustomNameVisible(true);
	}

	
	public Transistor(ArmorStand armor) {
		super(armor);
	}

	@Override
	public void redstoneUpdate() {
		if(checkForConnection(getLocation()) >= 1 && checkForConnectionRepeater(getLocation()) >= 1){
			turnLever(getLocation(), true);
			return;
		}
		turnLever(getLocation(), false);
	}

}
