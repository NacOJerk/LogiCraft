package com.kirelcodes.logicraft.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.kirelcodes.logicraft.components.Component;
import com.kirelcodes.logicraft.components.Transistor;
import com.kirelcodes.logicraft.components.gates.AND;
import com.kirelcodes.logicraft.components.gates.Bridge;
import com.kirelcodes.logicraft.components.gates.NAND;
import com.kirelcodes.logicraft.components.gates.NOR;
import com.kirelcodes.logicraft.components.gates.NOT;
import com.kirelcodes.logicraft.components.gates.OR;
import com.kirelcodes.logicraft.components.gates.XOR;

public class CommandLoader {
	public static void loadDemUP() {
		MajorCommand major = new MajorCommand("logicraft")
				.setArgsMessage("Dang bitch you better type some shit")
				.setNoPermission("No perms slut")
				.setNoSuchCommandMessage(
						"Dannnnnnng you trying to fuck with me ?")
				.setPrefix(ChatColor.AQUA + "[POKECRAFT]");
		CommandManager cm = major.getCommandManager();
		cm.addCommand(new ExtendedCommandBase("AND") {

			@Override
			public List<String> tabComplete(CommandSender sender, String alias,
					String[] args) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean executeCommand(CommandSender sender, String command,
					String[] args, boolean sentViaPlayer) {
				if (!sentViaPlayer)
					return false;
				Player p = (Player) sender;
				new AND(p.getLocation());
				return false;
			}
		});
		cm.addCommand(new ExtendedCommandBase("XOR") {

			@Override
			public List<String> tabComplete(CommandSender sender, String alias,
					String[] args) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean executeCommand(CommandSender sender, String command,
					String[] args, boolean sentViaPlayer) {
				if (!sentViaPlayer)
					return false;
				Player p = (Player) sender;
				new XOR(p.getLocation());
				return false;
			}
		});
		cm.addCommand(new ExtendedCommandBase("OR") {

			@Override
			public List<String> tabComplete(CommandSender sender, String alias,
					String[] args) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean executeCommand(CommandSender sender, String command,
					String[] args, boolean sentViaPlayer) {
				if (!sentViaPlayer)
					return false;
				Player p = (Player) sender;
				new OR(p.getLocation());
				return false;
			}
		});
		cm.addCommand(new ExtendedCommandBase("NOR") {

			@Override
			public List<String> tabComplete(CommandSender sender, String alias,
					String[] args) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean executeCommand(CommandSender sender, String command,
					String[] args, boolean sentViaPlayer) {
				if (!sentViaPlayer)
					return false;
				Player p = (Player) sender;
				new NOR(p.getLocation());
				return false;
			}
		});
		cm.addCommand(new ExtendedCommandBase("BRIDGE") {

			@Override
			public List<String> tabComplete(CommandSender sender, String alias,
					String[] args) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean executeCommand(CommandSender sender, String command,
					String[] args, boolean sentViaPlayer) {
				if (!sentViaPlayer)
					return false;
				Player p = (Player) sender;
				new Bridge(p.getLocation());
				return false;
			}
		});
		cm.addCommand(new ExtendedCommandBase("TRANSISTOR") {

			@Override
			public List<String> tabComplete(CommandSender sender, String alias,
					String[] args) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean executeCommand(CommandSender sender, String command,
					String[] args, boolean sentViaPlayer) {
				if (!sentViaPlayer)
					return false;
				Player p = (Player) sender;
				new Transistor(p.getLocation());
				return false;
			}
		});

		cm.addCommand(new ExtendedCommandBase("NAND") {

			@Override
			public List<String> tabComplete(CommandSender sender, String alias,
					String[] args) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean executeCommand(CommandSender sender, String command,
					String[] args, boolean sentViaPlayer) {
				if (!sentViaPlayer)
					return false;
				Player p = (Player) sender;
				new NAND(p.getLocation());
				return false;
			}
		});

		cm.addCommand(new ExtendedCommandBase("NOT") {

			@Override
			public List<String> tabComplete(CommandSender sender, String alias,
					String[] args) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean executeCommand(CommandSender sender, String command,
					String[] args, boolean sentViaPlayer) {
				if (!sentViaPlayer)
					return false;
				Player p = (Player) sender;
				new NOT(p.getLocation());
				return false;
			}
		});
		cm.addCommand(new ExtendedCommandBase("clearGates") {

			@Override
			public List<String> tabComplete(CommandSender sender, String alias,
					String[] args) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean executeCommand(CommandSender sender, String command,
					String[] args, boolean sentViaPlayer) {
				if (!sentViaPlayer)
					return false;
				Player p = (Player) sender;
				for (Entity e : p.getNearbyEntities(2, 2, 2)) {
					if (!(e instanceof ArmorStand))
						continue;
					if (Component.getComponent((ArmorStand) e) == null)
						continue;
					e.remove();
				}
				return false;
			}
		});

	}

}
