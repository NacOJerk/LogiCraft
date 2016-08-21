package com.kirelcodes.logicraft.components.gates;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import com.kirelcodes.logicraft.components.Component;

public abstract class Gate extends Component{
	public Gate(Location loc , Material m , String name){
		super(loc);
		getComponent().setHelmet(new ItemStack(m));
		getComponent().setCustomName(name);
		getComponent().setCustomNameVisible(true);

	}
	public Gate(ArmorStand armor) {
		super(armor);
	}
	
}
