package com.kirelcodes.logicraft.components.latchs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import com.kirelcodes.logicraft.components.Component;

public abstract class Latch extends Component {

	public Latch(Location loc , Material m , String name){
		super(loc);
		getComponent().setHelmet(new ItemStack(m));
		getComponent().setCustomName(name);
		getComponent().setCustomNameVisible(true);
	}
	
	
	public Latch(ArmorStand armor) {
		super(armor);
	}

}
