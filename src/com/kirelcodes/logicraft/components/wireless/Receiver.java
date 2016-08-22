package com.kirelcodes.logicraft.components.wireless;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import com.kirelcodes.logicraft.utils.NBTRW;

public class Receiver extends Wireless {

	public Receiver(Location loc) {
		super(loc, "Receiver");
		setStoredByte(false);
	}

	public Receiver(ArmorStand armor) {
		super(armor);
	}

	private boolean onOff() {
		ItemStack dataSaver = getComponent().getChestplate();
		try {
			if (!NBTRW.containsNBTTag(dataSaver, "storedByte")) {
				return false;
			}
			return NBTRW.getNBTBoolean(dataSaver, "storedByte");
		} catch (Exception e) {
			return false;
		}
	}

	private void setStoredByte(boolean onOf) {
		ItemStack dataSaver = getComponent().getChestplate();
		try {
			dataSaver = NBTRW.writeNBT(dataSaver, "storedByte", onOf);
			getComponent().setChestplate(dataSaver);
			redstoneUpdate();
		} catch (Exception e) {
			return;
		}

	}

	public void turnReceiver(boolean onOf){
		setStoredByte(onOf);
	}
	
	@Override
	public void redstoneUpdate() {
		turnLever(getLocation(), onOff());
	}

}
