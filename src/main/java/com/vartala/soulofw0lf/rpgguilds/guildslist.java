package com.vartala.soulofw0lf.rpgguilds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class guildslist implements CommandExecutor {
	RpgGuilds Rpgg;
	public guildslist(RpgGuilds rpgg){
		this.Rpgg = rpgg;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player)sender;
		Integer i = 0;
		for (String guilds : this.Rpgg.getConfig().getConfigurationSection("Guilds").getKeys(false)){
			i = 0;
			for (String players : this.Rpgg.getConfig().getConfigurationSection("Guilds." + guilds + ".Players").getKeys(false)){
				if (players != null){
					i++;
				}
				if (i == 1){
					player.sendMessage("§e" + guilds + ": §2" + i + " Player.");
				} else {
					player.sendMessage("§e" + guilds + ": §2" + i + " Players.");
				}

			}
		}
		return true;
	}
}
