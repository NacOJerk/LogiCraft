package com.kirelcodes.logicraft.components;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.kirelcodes.logicraft.LogiCraft;
import com.kirelcodes.logicraft.utils.NBTRW;

public class Delayer extends Component {

	public Delayer(Location loc, int delay) {
		super(loc);
		setHead(Material.STAINED_CLAY, 14);
		setName("Delayer");
		setDelay(delay);
		setDelay(false);
	}

	public Delayer(ArmorStand armor) {
		super(armor);
	}

	public int getDelay() {
		try {
			return NBTRW.getNBTInt(getDataSaver(), "logicraft.delay");
		} catch (Exception e) {
			return 0;
		}
	}

	public void setDelay(int delay) {
		ItemStack dataSaver = getDataSaver();
		try {
			setDataSaver(NBTRW.writeNBT(dataSaver, "logicraft.delay", delay));
		} catch (Exception e) {

		}
	}

	public boolean isInDelay() {
		try {
			return NBTRW.getNBTBoolean(getDataSaver(), "logicraft.inDelay");
		} catch (Exception e) {
			return false;
		}
	}

	public void setDelay(boolean delay) {
		try {
			setDataSaver(NBTRW.writeNBT(getDataSaver(), "logicraft.inDelay",
					delay));
		} catch (Exception e) {
		}
	}

	@Override
	public void redstoneUpdate() {
		if(isInDelay())
			return;
		if (checkForConnection(getLocation()) < 1) {
			turnLever(getLocation(), false);
			return;
		}
		setDelay(true);
		new BukkitRunnable() {

			@Override
			public void run() {
				turnLever(getLocation(), true);
				new BukkitRunnable() {

					@Override
					public void run() {
						if (checkForConnection(getLocation()) < 1)
							turnLever(getLocation(), false);
						setDelay(false);
					}
				}.runTaskLater(LogiCraft.getInstance(), 15L);
			}
		}.runTaskLater(LogiCraft.getInstance(), getDelay());
	}

}
