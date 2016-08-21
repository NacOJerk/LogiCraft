package com.kirelcodes.logicraft.components.latchs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import com.kirelcodes.logicraft.utils.NBTRW;

public class DLatch extends Latch {
	public DLatch(Location loc) {
		super(loc, Material.PUMPKIN, "D-LATCH");
		ItemStack dataSaver = getComponent().getChestplate();
		try {
			dataSaver = NBTRW.writeNBT(dataSaver, "storedByte", false);
		} catch (Exception e) {
			getComponent().remove();
			return;
		}
		getComponent().setChestplate(dataSaver);
	}

	public DLatch(ArmorStand armor) {
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
				getComponent().setHelmet( new ItemStack(Material.JACK_O_LANTERN));
			else
				getComponent().setHelmet( new ItemStack(Material.PUMPKIN));
			getComponent().setChestplate(dataSaver);
		}catch(Exception e){
			return;
		}

	}
	
	@Override
	public void redstoneUpdate() {
		int repeater = checkForConnectionRepeater(getLocation());
		int redstone = checkForConnection(getLocation());
		if(repeater >= 1){//WRITE MOD
			if(redstone >= 1)
				setStoredByte(true);
			else
				setStoredByte(false);
		}
		turnLever(getLocation(), onOff());
	}

}
