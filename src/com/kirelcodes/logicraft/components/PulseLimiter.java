package com.kirelcodes.logicraft.components;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.kirelcodes.logicraft.LogiCraft;
import com.kirelcodes.logicraft.utils.NBTRW;

public class PulseLimiter extends Component{

	public PulseLimiter(Location loc) {
		super(loc);
		getComponent().setHelmet(new ItemStack(Material.SOUL_SAND));
		getComponent().setCustomName("Pulse Limiter".toUpperCase());
		getComponent().setCustomNameVisible(true);
		setStoredByte(false);
	}
	
	public PulseLimiter(ArmorStand armor) {
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
				getComponent().setHelmet( new ItemStack(Material.SOUL_SAND));
			else
				getComponent().setHelmet( new ItemStack(Material.SOUL_SAND));
			getComponent().setChestplate(dataSaver);
		}catch(Exception e){
			return;
		}

	}

	@Override
	public void redstoneUpdate() {
		if(checkForConnection(getLocation())>0){
			if(onOff()){
				turnLever(getLocation(), false);
			}else{
				turnLever(getLocation(), true);
				new BukkitRunnable() {
					
					@Override
					public void run() {
						setStoredByte(true);
					}
				}.runTaskLater(LogiCraft.getInstance(), 1L);
				
			}
		}else{
			turnLever(getLocation(), false);
			setStoredByte(false);
		}
	}

}
