package com.kirelcodes.logicraft;

import org.bukkit.plugin.java.JavaPlugin;

import com.kirelcodes.logicraft.commands.CommandLoader;
import com.kirelcodes.logicraft.listeners.RedstoneComponentListener;

public class LogiCraft extends JavaPlugin{
	@Override
	public void onEnable() {
		load();
	}
	private void load(){
		new RedstoneComponentListener(this);
		CommandLoader.loadDemUP();
	}
}
