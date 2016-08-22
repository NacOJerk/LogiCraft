package com.kirelcodes.logicraft.components.flipflops;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import com.kirelcodes.logicraft.utils.NBTRW;

public class DFlipFlop extends FlipFlop {
	
	
	public DFlipFlop(Location loc) {
		super(loc, Material.REDSTONE_LAMP_OFF, "D-FlipFlop", (short)15);
		setLastRed(false);
		setStoredByte(false);
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
				getComponent().setHelmet( new ItemStack(Material.WOOL , 1 , (short)0));
			else
				getComponent().setHelmet( new ItemStack(Material.WOOL , 1 , (short) 15));
			getComponent().setChestplate(dataSaver);
		}catch(Exception e){
			return;
		}

	}
	private void setLastRed(boolean onOf){
		ItemStack dataSaver = getComponent().getChestplate();
		try{
			dataSaver = NBTRW.writeNBT(dataSaver, "lastRed", onOf);
			getComponent().setChestplate(dataSaver);
		}catch(Exception e){
			return;
		}

	}
	private boolean getLastRed(){
		ItemStack dataSaver = getComponent().getChestplate();
		try{
			if(!NBTRW.containsNBTTag(dataSaver, "lastRed")){
				return false;
			}
			return NBTRW.getNBTBoolean(dataSaver, "lastRed");
		}catch(Exception e){
			return false;
		}
	}
	@Override
	public void redstoneUpdate() {
		int repeater = checkForConnectionRepeater(getLocation());
		int redstone = checkForConnection(getLocation());
		if(!getLastRed() && redstone >= 1){
			if(repeater >= 1)
				setStoredByte(true);
			else
				setStoredByte(false);
		}
		turnLever(getLocation(), onOff());
		setLastRed(redstone > 0);
	}

}
