package com.vartala.soulofw0lf.rpgguilds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class gBankHandler implements CommandExecutor {

	RpgGuilds Rpgg;
	
	public gBankHandler(RpgGuilds rpgg){
		this.Rpgg = rpgg;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		//gbank //add //rank access //withdrawl //deposit
		return false;
	}

}
