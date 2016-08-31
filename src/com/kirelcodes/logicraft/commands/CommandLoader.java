package com.kirelcodes.logicraft.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.kirelcodes.logicraft.components.Component;
import com.kirelcodes.logicraft.components.Delayer;
import com.kirelcodes.logicraft.components.PulseLimiter;
import com.kirelcodes.logicraft.components.Transistor;
import com.kirelcodes.logicraft.components.flipflops.DFlipFlop;
import com.kirelcodes.logicraft.components.flipflops.TFlipFlop;
import com.kirelcodes.logicraft.components.gates.AND;
import com.kirelcodes.logicraft.components.gates.Bridge;
import com.kirelcodes.logicraft.components.gates.NAND;
import com.kirelcodes.logicraft.components.gates.NOR;
import com.kirelcodes.logicraft.components.gates.NOT;
import com.kirelcodes.logicraft.components.gates.OR;
import com.kirelcodes.logicraft.components.gates.XOR;
import com.kirelcodes.logicraft.components.latchs.DLatch;
import com.kirelcodes.logicraft.components.wireless.Receiver;
import com.kirelcodes.logicraft.components.wireless.Transmitter;

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
		cm.addCommand(new ExtendedCommandBase("Transmitter".toUpperCase()) {

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
				int x = Integer.parseInt(args[0]);
				int y = Integer.parseInt(args[1]);
				int z = Integer.parseInt(args[2]);
				new Transmitter(p.getLocation(), x, y, z);
				return false;
			}
		});
		cm.addCommand(new ExtendedCommandBase("delayer".toUpperCase()) {

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
				int delay = Integer.parseInt(args[0]);
				new Delayer(p.getLocation(), delay);
				return false;
			}
		});
		cm.addCommand(new ExtendedCommandBase("PulseLimiter".toUpperCase()) {

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
				new PulseLimiter(p.getLocation());
				return false;
			}
		});
		cm.addCommand(new ExtendedCommandBase("reciever") {

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
				new Receiver(p.getLocation());
				return false;
			}
		});
		cm.addCommand(new ExtendedCommandBase("DFLIP") {

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
				new DFlipFlop(p.getLocation());
				return false;
			}
		});
		cm.addCommand(new ExtendedCommandBase("TFLIP") {

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
				new TFlipFlop(p.getLocation());
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
		cm.addCommand(new ExtendedCommandBase("DLATCH") {

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
				new DLatch(p.getLocation());
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
