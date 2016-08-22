package com.kirelcodes.logicraft.components.wireless;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import com.kirelcodes.logicraft.components.Component;
import com.kirelcodes.logicraft.utils.NBTRW;

public class Transmitter extends Wireless {
	public Transmitter(Location loc, int x, int y, int z) {
		super(loc, "Transmitter");
		setXYZ(x, y, z);
	}

	public Transmitter(Location loc) {
		super(loc, "Transmitter");
		setXYZ(0, 0, 0);
	}

	public void setXYZ(int x, int y, int z) {
		ItemStack dataSaver = getComponent().getChestplate();
		try {
			dataSaver = NBTRW.writeNBT(dataSaver, "transmitterX", x);
			dataSaver = NBTRW.writeNBT(dataSaver, "transmitterY", y);
			dataSaver = NBTRW.writeNBT(dataSaver, "transmitterZ", z);
			getComponent().setChestplate(dataSaver);
		} catch (Exception e) {
			return;
		}

	}

	public Location geReceivertLocation() {
		ItemStack dataSaver = getComponent().getChestplate();
		try {
			Location loc = new Location(getComponent().getWorld(),
					NBTRW.getNBTInt(dataSaver, "transmitterX"),
					NBTRW.getNBTInt(dataSaver, "transmitterY"),
					NBTRW.getNBTInt(dataSaver, "transmitterZ"));
			return loc;
		} catch (Exception e) {
			return null;
		}
	}

	public Transmitter(ArmorStand armor) {
		super(armor);
	}

	private void setReceiver(boolean bool) {
		List<Entity> entites = getNearByEntities(geReceivertLocation().clone().add(0.5, -0.5, 0.5), 1);
		for (Entity e : entites) {
			if (!(e instanceof ArmorStand))
				continue;
			Component comp = null;
			if ((comp = getComponent((ArmorStand) e)) == null)
				continue;
			if(!(comp instanceof Receiver))
				continue;
			Receiver reciver = (Receiver) comp;
			reciver.turnReceiver(bool);
		}
	}

	@Override
	public void redstoneUpdate() {
		if (checkForConnection(getLocation()) > 0)
			setReceiver(true);
		else
			setReceiver(false);
		turnLever(getLocation(), false);
	}

}
