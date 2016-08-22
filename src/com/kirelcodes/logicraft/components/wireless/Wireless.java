package com.kirelcodes.logicraft.components.wireless;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import com.kirelcodes.logicraft.components.Component;

public abstract class Wireless extends Component{

	public Wireless(Location loc , String name){
		super(loc);
		getComponent().setHelmet(new ItemStack(Material.BEACON));
		getComponent().setCustomName(name);
		getComponent().setCustomNameVisible(true);
	}

	
	public Wireless(ArmorStand armor) {
		super(armor);
	}

}
