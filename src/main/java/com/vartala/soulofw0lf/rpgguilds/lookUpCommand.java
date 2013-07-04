package com.vartala.soulofw0lf.rpgguilds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class lookUpCommand implements CommandExecutor {
	RpgGuilds Rpgg;
	public lookUpCommand(RpgGuilds rpgGuilds) {
		this.Rpgg = rpgGuilds;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player p = (Player)sender;
		if (args.length != 1){
			p.sendMessage("Improper usage! Please use /lookup {Player name}");
			return true;
		}
		if (Bukkit.getPlayer(args[0]) == null){
			p.sendMessage("This player could not be found");
			return true;
		}
		Player player = Bukkit.getPlayer(args[0]);
		double money = RpgGuilds.econ.getBalance(player.getName());
		
		Integer kills = this.Rpgg.getConfig().getInt("Kills." + player.getName());
		p.sendMessage("§APlayer Lookup: §e" + player.getName());
		if (this.Rpgg.getConfig().contains(player.getName())){
			String guild = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
			p.sendMessage("§APlayer Lookup: §5Guild - " + guild);
		}
		p.sendMessage("§APlayer Lookup: §4Kills - " + kills);
		p.sendMessage("§APlayer Lookup: §2Money - " + money);
		return true;
		
	}

}
