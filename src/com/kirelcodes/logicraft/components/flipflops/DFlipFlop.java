package com.kirelcodes.logicraft.components.flipflops;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import com.kirelcodes.logicraft.utils.NBTRW;

public class DFlipFlop extends FlipFlop {
	
	private boolean lastRed = false;
	
	public DFlipFlop(Location loc) {
		super(loc, Material.REDSTONE_LAMP_OFF, "D-FlipFlop");
		ItemStack dataSaver = getComponent().getChestplate();
		try {
			dataSaver = NBTRW.writeNBT(dataSaver, "storedByte", false);
		} catch (Exception e) {
			getComponent().remove();
			return;
		}
		getComponent().setChestplate(dataSaver);
	}

	public DFlipFlop(ArmorStand armor) {
		super(armor);
	}

	private boolean onOff(){
		ItemStack dataSaver = getComponent().getChestplate();
		try{
			if(!NBTRW.containsNBTTag(dataSaver, "storedByte")){
				return false;
			}
			return NBTRW.getNBTBoolean(dataSaver, "storedByte");
		}catch(Exception e){
			return false;
		}
	}
	
	private void setStoredByte(boolean onOf){
		ItemStack dataSaver = getComponent().getChestplate();
		try{
			dataSaver = NBTRW.writeNBT(dataSaver, "storedByte", onOf);
			if(onOf)
				getComponent().setHelmet( new ItemStack(Material.REDSTONE_LAMP_ON));
			else
				getComponent().setHelmet( new ItemStack(Material.REDSTONE_LAMP_OFF));
			getComponent().setChestplate(dataSaver);
		}catch(Exception e){
			return;
		}

	}
	
	@Override
	public void redstoneUpdate() {
		int repeater = checkForConnectionRepeater(getLocation());
		int redstone = checkForConnection(getLocation());
		if(!lastRed && redstone >= 1){
			if(repeater >= 1)
				setStoredByte(true);
			else
				setStoredByte(false);
		}
		turnLever(getLocation(), onOff());
		lastRed = redstone > 0;
	}

}
