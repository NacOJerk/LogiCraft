package com.kirelcodes.logicraft;

import org.bukkit.plugin.java.JavaPlugin;

import com.kirelcodes.logicraft.commands.CommandLoader;
import com.kirelcodes.logicraft.listeners.RedstoneComponentListener;
import com.kirelcodes.logicraft.listeners.RedstoneUpdateContainer;

public class LogiCraft extends JavaPlugin{
	private static LogiCraft instance = null;
	@Override
	public void onEnable() {
		instance = this;
		load();
	}
	public static LogiCraft getInstance(){
		return instance;
	}
	private void load(){
		new RedstoneUpdateContainer();
		new RedstoneComponentListener(this);
		CommandLoader.loadDemUP();
	}
}
