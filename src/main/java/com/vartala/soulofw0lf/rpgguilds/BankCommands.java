package com.vartala.soulofw0lf.rpgguilds;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class BankCommands {
	
	public static boolean bankCommands(CommandSender sender, String[] args){
		if(!(sender instanceof Player))
			return true;
		ActionManager.openBank(((Player)sender).getName());
		return true;
	}

}
