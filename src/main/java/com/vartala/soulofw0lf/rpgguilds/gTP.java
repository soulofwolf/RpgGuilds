package com.vartala.soulofw0lf.rpgguilds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gTP implements CommandExecutor {
	RpgGuilds Rpgg;
	public gTP(RpgGuilds rpgg){
		this.Rpgg = rpgg;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if (!(player.hasPermission("guild.teleport"))){
			player.sendMessage("You do not have permission to teleport people on this server!");
			return true;
		}
		if (args.length == 0){
			return false;
		}
		if (this.Rpgg.getConfig().getBoolean("TP") == false){
			player.sendMessage("Teleporting guild members is disabled on this server!");
			return true;
		}
		if (Bukkit.getPlayer(args[0]) == null){
			player.sendMessage("That player cannot be found");
			return true;
		}
		Player p = Bukkit.getPlayer(args[0]);
		String pi = p.getName();
		
		if (!(this.Rpgg.getConfig().contains(pi))){
			player.sendMessage("This player is not even in a guild!");
			return true;
		}
		if (!(this.Rpgg.getConfig().contains(player.getName()))){
			player.sendMessage("You are not even in a guild? how can you invite someone?");
			return true;
		}
		String guildn = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
		String guildn1 = this.Rpgg.getConfig().getString(pi + ".Guild.Name");
		String grank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + player.getName() + ".Rank");
		if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + grank + ".TP") == false){
			player.sendMessage("You do not have permission to teleport people in this guild!");
			return true;
		}
		if (!(guildn.equalsIgnoreCase(guildn1))){
			player.sendMessage("This player is not in your guild, you can only teleport members of your own guild!");
			return true;
		}
		this.Rpgg.teleport(player, p);
		return true;
	}
}
