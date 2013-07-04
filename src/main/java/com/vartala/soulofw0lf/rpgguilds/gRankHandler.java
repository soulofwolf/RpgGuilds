package com.vartala.soulofw0lf.rpgguilds;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class gRankHandler implements CommandExecutor {
	RpgGuilds Rpgg;
	public gRankHandler(RpgGuilds rpgg){
		this.Rpgg = rpgg;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		//set //title //create //delete
		if (args.length <= 0){
			return false;
		}
		if (args[0].equalsIgnoreCase("create")){
			Player player = (Player) sender;
			if (args.length != 3){
				player.sendMessage("Improper usage! Please use /grank create rank title");
				return true;
			}
			String guildn = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + player.getName() + ".Rank");
			if (!(this.Rpgg.getConfig().contains(player.getName()))){
				player.sendMessage("You are not even in a guild? how can you expect to create a rank one?");
				return true;
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + grank + ".CreateRank") == false){
				player.sendMessage("You do not have permission to create a rank in this guild!");
				return true;
			}
			if (this.Rpgg.getConfig().contains("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " "))){
				player.sendMessage("This rank already exists!");
				return true;
			}
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Title", args[2].replaceAll("_", " ").replaceAll("&", "§"));
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Invite", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Kick", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Gmotd", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Disband", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Gchat", true);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Gbank", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Addslot", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Withdrawl", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Deposit", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".GbRanks", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".RankSet", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".RankTitle", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".CreateRank", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".DeleteRank", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".PlayerInfo", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".RankPerms", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Ochat", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".PlayerNotes", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".PlayerNotesView", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".PlayerNotesSet", false);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".TP", false);
			this.Rpgg.saveConfig();
			player.sendMessage("You have successfully created the rank " + args[1].replaceAll("_", " ") + "!");
			return true;
		}
		if (args[0].equalsIgnoreCase("delete")){
			Player player = (Player) sender;
			if (args.length <=1){
				player.sendMessage("You must include a rank to delete!");
				return true;
			}
			String guildn = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + player.getName() + ".Rank");
			if (!(this.Rpgg.getConfig().contains(player.getName()))){
				player.sendMessage("You are not even in a guild? how can you expect to delete a rank?");
				return true;
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + grank + ".DeleteRank") == false){
				player.sendMessage("You do not have permission to delete a rank in this guild!");
				return true;
			}
			if (!(this.Rpgg.getConfig().contains("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ")))){
				player.sendMessage("This rank doesn't exist!");
				return true;
			}
			String newbies = this.Rpgg.getConfig().getString("Guilds." + guildn + ".DefTerm.Default");
			for (String key : this.Rpgg.getConfig().getConfigurationSection("Guilds." + guildn + ".Players").getKeys(false)){
				String rank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + key + ".Rank");
				if (args[1].replaceAll("_", " ").equalsIgnoreCase(rank)){
					this.Rpgg.getConfig().set("Guilds." + guildn + ".Players." + key + ".Rank", newbies);
					if (Bukkit.getPlayer(key) != null){
						Player p = Bukkit.getPlayer(key);
						p.sendMessage("§3 Your rank has been changed to " + newbies + "!");
					}
				}
			}
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " "), null);
			this.Rpgg.saveConfig();
			player.sendMessage("You have successfully deleted the rank " + args[1].replaceAll("_", " ") + "!");
			return true;
		}
		if (args[0].equalsIgnoreCase("set")){
			Player player = (Player) sender;
			if (args.length != 3){
				player.sendMessage("Improper usage! Please use /grank set playername rank");
				return true;
			}
			String guildn = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + player.getName() + ".Rank");
			String prank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + args[1] + ".Rank");
			
			if (!(this.Rpgg.getConfig().contains(player.getName()))){
				player.sendMessage("You are not even in a guild? how can you expect to delete a rank?");
				return true;
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + grank + ".RankSet") == false){
				player.sendMessage("You do not have permission to set a players rank in this guild!");
				return true;
			}
			if (!(this.Rpgg.getConfig().contains("Guilds." + guildn + ".Ranks." + args[2].replaceAll("_", " ")))){
				player.sendMessage("This rank doesn't exist!");
				return true;
			}
			if (!(this.Rpgg.getConfig().contains("Guilds." + guildn + ".Players." + args[1]))){
				player.sendMessage("That player is not a part of your guild!");
				return true;
			}
			String leader = this.Rpgg.getConfig().getString("Guilds." + guildn + ".DefTerm.Leader");
			player.sendMessage("grank = " + grank + "prank = " + prank + "guiln = " + guildn + " leader = " + leader);
			String newbie = this.Rpgg.getConfig().getString("Guilds." + guildn + ".DefTerm.Default");
			if ((args[2].replaceAll("_", " ").equalsIgnoreCase(leader) && (!(grank.equalsIgnoreCase(leader))))){
				player.sendMessage("You can not promote someone to Guild Leader this way!!");
				return true;
			}
			if ((args[2].replaceAll("_", " ").equalsIgnoreCase(leader) && (grank.equalsIgnoreCase(leader)))){
				this.Rpgg.getConfig().set("Guilds." + guildn + ".Players." + args[1] + ".Rank", leader);
				this.Rpgg.getConfig().set("Guilds." + guildn + ".Players." + player.getName() + ".Rank", newbie);
				this.Rpgg.getConfig().set("Guilds." + guildn + ".Leader", args[1]);
				this.Rpgg.saveConfig();
				player.sendMessage("You are no longer the leader of " + guildn + ".");
				for (String key : this.Rpgg.getConfig().getConfigurationSection("Guilds." + guildn + ".Players").getKeys(false)){
					if (Bukkit.getPlayer(key) != null){
						Player p = Bukkit.getPlayer(key);
						if (!(p.getName().equalsIgnoreCase(args[1]))){
							p.sendMessage(args[1] + " is the new Leader of " + guildn + ".");
						} else {
							p.sendMessage("You are the new Guild leader of " + guildn + "!");
						}
					}
				}
				return true;
			}
			if (prank.equalsIgnoreCase(grank)){
				player.sendMessage("You can not change someone's rank if their rank is the same as yours!");
				return true;
			}
			if (args[2].replaceAll("_" , " ").equalsIgnoreCase(grank)){
				player.sendMessage("You can not set someone to the same rank as yours!");
				return true;
			}

			if (prank.equalsIgnoreCase(leader)){
				player.sendMessage("You cannot change a guild leaders rank in this fashion!");
				return true;
			}
			for (String key : this.Rpgg.getConfig().getConfigurationSection("Guilds." + guildn + ".Players").getKeys(false)){
				if (Bukkit.getPlayer(key) != null){
					Player p = Bukkit.getPlayer(key);
					if (!(p.getName().equalsIgnoreCase(args[1]))){
						p.sendMessage(args[1] + " has been changed to the rank " + args[2].replaceAll("_", " ") + ".");
					} else {
						p.sendMessage("You have been moved to the rank " + args[2].replaceAll("_", " ") + ".");
					}
				}
			}
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Players." + args[1] + ".Rank", args[2].replaceAll("_", " "));

			this.Rpgg.saveConfig();
			return true;
		}
		if (args[0].equalsIgnoreCase("title")){
			Player player = (Player) sender;
			if (args.length != 3){
				player.sendMessage("Improper usage! Please use /grank title rankname newtitle");
				return true;
			}
			String guildn = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + player.getName() + ".Rank");
			if (!(this.Rpgg.getConfig().contains(player.getName()))){
				player.sendMessage("You are not even in a guild? how can you expect to delete a rank?");
				return true;
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + grank + ".RankTitle") == false){
				player.sendMessage("You do not have permission to set a rank's Title in this guild!");
				return true;
			}
			if (!(this.Rpgg.getConfig().contains("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ")))){
				player.sendMessage("This rank doesn't exist!");
				return true;
			}
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Title", args[2].replaceAll("_", " ").replaceAll("&", "§"));
			this.Rpgg.saveConfig();
			player.sendMessage("You have changed " + args[1].replaceAll("_", " ") + "'s title to " + args[2].replaceAll("_", " ").replaceAll("&", "§") + ".");
			return true;
		}
		if (args[0].equalsIgnoreCase("perms")){
			Player player = (Player) sender;
			if (args.length != 4){
				player.sendMessage("Improper usage! Please use /grank perms rankname permission_name true/false");
				return true;
			}
			String guildn = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + player.getName() + ".Rank");
			if (!(this.Rpgg.getConfig().contains(player.getName()))){
				player.sendMessage("You are not even in a guild? how can you expect to delete a rank?");
				return true;
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + grank + ".RankPerms") == false){
				player.sendMessage("You do not have permission to set a rank's permissions in this guild!");
				return true;
			}
			if (!(this.Rpgg.getConfig().contains("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ")))){
				player.sendMessage("This rank doesn't exist!");
				return true;
			}
			String leader = this.Rpgg.getConfig().getString("Guild." + guildn + ".DefTerm.Leader");
			if (args[1].replaceAll("_", " ").equalsIgnoreCase(leader)){
				player.sendMessage("You can not change a guild leaders permissions!");
				return true;
			}
			if (args[3].equalsIgnoreCase("true")){
				Boolean permbool = true;
				this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + "." + args[2].replaceAll("_", " "), permbool);
				this.Rpgg.saveConfig();
				player.sendMessage("Members with the rank " + args[1].replaceAll("_", " ") + " now have  " + args[2].replaceAll("_", " ") + " permissions.");
				return true;
			} else {
				Boolean permbool = false;
				this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + "." + args[2].replaceAll("_", " "), permbool);
				this.Rpgg.saveConfig();
				player.sendMessage("Members with the rank " + args[1].replaceAll("_", " ") + " no longer have  " + args[2].replaceAll("_", " ") + " permissions.");
				return true;
			}

		}
		if (args[0].equalsIgnoreCase("list")){
			Player player = (Player) sender;
			player.sendMessage("The available rank permissions are \nInvite\nKick\nGmotd\nDisband\nGchat\nGbank\nAddslot\nWithdrawl\nDeposit\nGbRanks\nRankSet\nRankTitle\nCreateRank\nDeleteRank\nPlayerInfo\nRankPerms\nOchat\nPlayerNotesView\nPlayerNotesSet\nTitle");
			return true;
		}
		if (args[0].equalsIgnoreCase("defaults")){
			Player player = (Player) sender;
			
			String guildn = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + player.getName() + ".Rank");
			String leader = this.Rpgg.getConfig().getString("Guilds." + guildn + ".DefTerm.Leader");
			if (!(grank.equalsIgnoreCase(leader))){
				player.sendMessage("Only a guild leader can change the default rank names!");
				return true;
			}
			if (args[1].equalsIgnoreCase("leader")){
				this.Rpgg.getConfig().set("Guilds." + guildn + ".DefTerm.Leader", args[2].replaceAll("&", "§").replaceAll("_", " "));
				this.Rpgg.saveConfig();
				player.sendMessage("You have changed the leader rank name to " + args[2].replaceAll("&", "§").replaceAll("_", " ") + ".");
				return true;
			}
			if (args[1].equalsIgnoreCase("newbies")){
				this.Rpgg.getConfig().set("Guilds." + guildn + ".DefTerm.Default", args[2].replaceAll("&", "§").replaceAll("_", " "));
				this.Rpgg.saveConfig();
				player.sendMessage("You have changed the default rank name to " + args[2].replaceAll("&", "§").replaceAll("_", " ") + ".");
				return true;
			}
			player.sendMessage("Improper usage! Please use /grank defaults {leader/newbies} new_rank_name");
			return true;
		}
		if (args[0].equalsIgnoreCase("ranks")){
			Player player = (Player)sender;
			String guildn = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + player.getName() + ".Rank");
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + grank + ".RankPerms") == false){
				player.sendMessage("You do not have permission to set a rank's permissions in this guild!");
				return true;
			}
			Inventory inv = Bukkit.createInventory(null, 45, "Ranks:");
			Integer i = 0;
			for (String ranks : this.Rpgg.getConfig().getConfigurationSection("Guilds." + guildn + ".Ranks").getKeys(false)){
				ItemStack itm = new ItemStack(Material.BOOK, 1);
				ItemMeta invite = itm.getItemMeta();
				invite.setDisplayName(ranks);
				itm.setItemMeta(invite);
				inv.setItem(i, itm);
				i++;
			}
			player.openInventory(inv);
			return true;
		}
		if (args[0].equalsIgnoreCase("edit")){
			Player player = (Player) sender;
			if (args.length != 2){
				player.sendMessage("You must specify a rank to edit!");
				return true;
			}
			String guildn = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + player.getName() + ".Rank");
			String leader = this.Rpgg.getConfig().getString("Guilds." + guildn + ".DefTerm.Leader");
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + grank + ".RankPerms") == false){
				player.sendMessage("You do not have permission to set a rank's permissions in this guild!");
				return true;
			}
			if (!(this.Rpgg.getConfig().contains("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ")))){
				player.sendMessage("This rank doesn't exist!");
				return true;
			}
			if (grank.equalsIgnoreCase(args[1].replaceAll("_", " "))){
				player.sendMessage("You cannot change permission for your own rank!");
				return true;
			}
			if (leader.equalsIgnoreCase(args[1].replaceAll("_", " "))){
				player.sendMessage("A guild leaders default permissions cannot be changed!!");
				return true;
			}
			Inventory inv = Bukkit.createInventory(null, 27, args[1].replaceAll("_", " "));
			ItemStack itmStack0 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack1 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack2 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack3 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack4 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack5 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack6 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack7 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack8 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack9 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack10 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack11 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack12 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack13 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack14 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack15 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack16 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack17 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack18 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack19 = new ItemStack(Material.WOOL, 1);
			Short green = 5;
			Short red = 14;
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Invite") == false){
				ItemMeta invite = itmStack0.getItemMeta();
				invite.setDisplayName("Invite");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				invite.setLore(lore);
				itmStack0.setItemMeta(invite);
				itmStack0.setDurability(red);
				inv.setItem(0, itmStack0);
			} else {
				ItemMeta invite = itmStack0.getItemMeta();
				invite.setDisplayName("Invite");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				invite.setLore(lore);
				itmStack0.setItemMeta(invite);
				itmStack0.setDurability(green);
				inv.setItem(0, itmStack0);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Kick") == false){
				ItemMeta kick = itmStack1.getItemMeta();
				kick.setDisplayName("Kick");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				kick.setLore(lore);
				itmStack1.setItemMeta(kick);
				itmStack1.setDurability(red);
				inv.setItem(1, itmStack1);
			} else {
				ItemMeta kick = itmStack1.getItemMeta();
				kick.setDisplayName("Kick");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				kick.setLore(lore);
				itmStack1.setItemMeta(kick);
				itmStack1.setDurability(green);
				inv.setItem(1, itmStack1);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Gmotd") == false){
				ItemMeta gmotd = itmStack2.getItemMeta();
				gmotd.setDisplayName("Gmotd");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				gmotd.setLore(lore);
				itmStack2.setItemMeta(gmotd);
				itmStack2.setDurability(red);
				inv.setItem(2, itmStack2);
			} else {
				ItemMeta gmotd = itmStack2.getItemMeta();
				gmotd.setDisplayName("Gmotd");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				gmotd.setLore(lore);
				itmStack2.setItemMeta(gmotd);
				itmStack2.setDurability(green);
				inv.setItem(2, itmStack2);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Disband") == false){
				ItemMeta disband = itmStack3.getItemMeta();
				disband.setDisplayName("Disband");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				disband.setLore(lore);
				itmStack3.setItemMeta(disband);
				itmStack3.setDurability(red);
				inv.setItem(3, itmStack3);
			} else {
				ItemMeta disband = itmStack3.getItemMeta();
				disband.setDisplayName("Disband");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				disband.setLore(lore);
				itmStack3.setItemMeta(disband);
				itmStack3.setDurability(green);
				inv.setItem(3, itmStack3);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Gchat") == false){
				ItemMeta gchat = itmStack4.getItemMeta();
				gchat.setDisplayName("Gchat");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				gchat.setLore(lore);
				itmStack4.setItemMeta(gchat);
				itmStack4.setDurability(red);
				inv.setItem(4, itmStack4);
			} else {
				ItemMeta gchat = itmStack4.getItemMeta();
				gchat.setDisplayName("Gchat");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				gchat.setLore(lore);
				itmStack4.setItemMeta(gchat);
				itmStack4.setDurability(green);
				inv.setItem(4, itmStack4);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Gbank") == false){
				ItemMeta gbank = itmStack5.getItemMeta();
				gbank.setDisplayName("Gbank");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				gbank.setLore(lore);
				itmStack5.setItemMeta(gbank);
				itmStack5.setDurability(red);
				inv.setItem(5, itmStack5);
			} else {
				ItemMeta gbank = itmStack5.getItemMeta();
				gbank.setDisplayName("Gbank");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				gbank.setLore(lore);
				itmStack5.setItemMeta(gbank);
				itmStack5.setDurability(green);
				inv.setItem(5, itmStack5);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Addslot") == false){
				ItemMeta addslot = itmStack6.getItemMeta();
				addslot.setDisplayName("Addslot");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				addslot.setLore(lore);
				itmStack6.setItemMeta(addslot);
				itmStack6.setDurability(red);
				inv.setItem(6, itmStack6);
			} else {
				ItemMeta addslot = itmStack6.getItemMeta();
				addslot.setDisplayName("Addslot");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				addslot.setLore(lore);
				itmStack6.setItemMeta(addslot);
				itmStack6.setDurability(green);
				inv.setItem(6, itmStack6);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Withdrawl") == false){
				ItemMeta withdrawl = itmStack7.getItemMeta();
				withdrawl.setDisplayName("Withdrawl");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				withdrawl.setLore(lore);
				itmStack7.setItemMeta(withdrawl);
				itmStack7.setDurability(red);
				inv.setItem(7, itmStack7);
			} else {
				ItemMeta withdrawl = itmStack7.getItemMeta();
				withdrawl.setDisplayName("Withdrawl");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				withdrawl.setLore(lore);
				itmStack7.setItemMeta(withdrawl);
				itmStack7.setDurability(green);
				inv.setItem(7, itmStack7);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Deposit") == false){
				ItemMeta deposit = itmStack8.getItemMeta();
				deposit.setDisplayName("Deposit");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				deposit.setLore(lore);
				itmStack8.setItemMeta(deposit);
				itmStack8.setDurability(red);
				inv.setItem(8, itmStack8);
			} else {
				ItemMeta deposit = itmStack8.getItemMeta();
				deposit.setDisplayName("Deposit");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				deposit.setLore(lore);
				itmStack8.setItemMeta(deposit);
				itmStack8.setDurability(green);
				inv.setItem(8, itmStack8);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".GbRanks") == false){
				ItemMeta GbRanks = itmStack9.getItemMeta();
				GbRanks.setDisplayName("GbRanks");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				GbRanks.setLore(lore);
				itmStack9.setItemMeta(GbRanks);
				itmStack9.setDurability(red);
				inv.setItem(9, itmStack9);
			} else {
				ItemMeta GbRanks = itmStack9.getItemMeta();
				GbRanks.setDisplayName("GbRanks");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				GbRanks.setLore(lore);
				itmStack9.setItemMeta(GbRanks);
				itmStack9.setDurability(green);
				inv.setItem(9, itmStack9);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".RankSet") == false){
				ItemMeta Rankset = itmStack10.getItemMeta();
				Rankset.setDisplayName("RankSet");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				Rankset.setLore(lore);
				itmStack10.setItemMeta(Rankset);
				itmStack10.setDurability(red);
				inv.setItem(10, itmStack10);
			} else {
				ItemMeta Rankset = itmStack10.getItemMeta();
				Rankset.setDisplayName("RankSet");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				Rankset.setLore(lore);
				itmStack10.setItemMeta(Rankset);
				itmStack10.setDurability(green);
				inv.setItem(10, itmStack10);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".RankTitle") == false){
				ItemMeta RankTitle = itmStack11.getItemMeta();
				RankTitle.setDisplayName("RankTitle");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				RankTitle.setLore(lore);
				itmStack11.setItemMeta(RankTitle);
				itmStack11.setDurability(red);
				inv.setItem(11, itmStack11);
			} else {
				ItemMeta RankTitle = itmStack11.getItemMeta();
				RankTitle.setDisplayName("RankTitle");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				RankTitle.setLore(lore);
				itmStack11.setItemMeta(RankTitle);
				itmStack11.setDurability(green);
				inv.setItem(11, itmStack11);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".CreateRank") == false){
				ItemMeta CreateRank = itmStack12.getItemMeta();
				CreateRank.setDisplayName("CreateRank");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				CreateRank.setLore(lore);
				itmStack12.setItemMeta(CreateRank);
				itmStack12.setDurability(red);
				inv.setItem(12, itmStack12);
			} else {
				ItemMeta CreateRank = itmStack12.getItemMeta();
				CreateRank.setDisplayName("CreateRank");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				CreateRank.setLore(lore);
				itmStack12.setItemMeta(CreateRank);
				itmStack12.setDurability(green);
				inv.setItem(12, itmStack12);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".DeleteRank") == false){
				ItemMeta DeleteRank = itmStack13.getItemMeta();
				DeleteRank.setDisplayName("DeleteRank");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				DeleteRank.setLore(lore);
				itmStack13.setItemMeta(DeleteRank);
				itmStack13.setDurability(red);
				inv.setItem(13, itmStack13);
			} else {
				ItemMeta DeleteRank = itmStack13.getItemMeta();
				DeleteRank.setDisplayName("DeleteRank");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				DeleteRank.setLore(lore);
				itmStack13.setItemMeta(DeleteRank);
				itmStack13.setDurability(green);
				inv.setItem(13, itmStack13);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".PlayerInfo") == false){
				ItemMeta PlayerInfo = itmStack14.getItemMeta();
				PlayerInfo.setDisplayName("PlayerInfo");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				PlayerInfo.setLore(lore);
				itmStack14.setItemMeta(PlayerInfo);
				itmStack14.setDurability(red);
				inv.setItem(14, itmStack14);
			} else {
				ItemMeta PlayerInfo = itmStack14.getItemMeta();
				PlayerInfo.setDisplayName("PlayerInfo");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				PlayerInfo.setLore(lore);
				itmStack14.setItemMeta(PlayerInfo);
				itmStack14.setDurability(green);
				inv.setItem(14, itmStack14);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".RankPerms") == false){
				ItemMeta RankPerms = itmStack15.getItemMeta();
				RankPerms.setDisplayName("RankPerms");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				RankPerms.setLore(lore);
				itmStack15.setItemMeta(RankPerms);
				itmStack15.setDurability(red);
				inv.setItem(15, itmStack15);
			} else {
				ItemMeta RankPerms = itmStack15.getItemMeta();
				RankPerms.setDisplayName("RankPerms");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				RankPerms.setLore(lore);
				itmStack15.setItemMeta(RankPerms);
				itmStack15.setDurability(green);
				inv.setItem(15, itmStack15);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".Ochat") == false){
				ItemMeta Ochat = itmStack16.getItemMeta();
				Ochat.setDisplayName("Ochat");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				Ochat.setLore(lore);
				itmStack16.setItemMeta(Ochat);
				itmStack16.setDurability(red);
				inv.setItem(16, itmStack16);
			} else {
				ItemMeta Ochat = itmStack16.getItemMeta();
				Ochat.setDisplayName("Ochat");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				Ochat.setLore(lore);
				itmStack16.setItemMeta(Ochat);
				itmStack16.setDurability(green);
				inv.setItem(16, itmStack16);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".PlayerNotes") == false){
				ItemMeta PlayerNotes = itmStack17.getItemMeta();
				PlayerNotes.setDisplayName("PlayerNotes");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				PlayerNotes.setLore(lore);
				itmStack17.setItemMeta(PlayerNotes);
				itmStack17.setDurability(red);
				inv.setItem(17, itmStack17);
			} else {
				ItemMeta PlayerNotes = itmStack17.getItemMeta();
				PlayerNotes.setDisplayName("PlayerNotes");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				PlayerNotes.setLore(lore);
				itmStack17.setItemMeta(PlayerNotes);
				itmStack17.setDurability(green);
				inv.setItem(17, itmStack17);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".PlayerNotesView") == false){
				ItemMeta PlayerNotesView = itmStack18.getItemMeta();
				PlayerNotesView.setDisplayName("PlayerNotesView");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				PlayerNotesView.setLore(lore);
				itmStack18.setItemMeta(PlayerNotesView);
				itmStack18.setDurability(red);
				inv.setItem(18, itmStack18);
			} else {
				ItemMeta PlayerNotesView = itmStack18.getItemMeta();
				PlayerNotesView.setDisplayName("PlayerNotesView");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				PlayerNotesView.setLore(lore);
				itmStack18.setItemMeta(PlayerNotesView);
				itmStack18.setDurability(green);
				inv.setItem(18, itmStack18);
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + args[1].replaceAll("_", " ") + ".TP") == false){
				ItemMeta TP = itmStack19.getItemMeta();
				TP.setDisplayName("TP");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				TP.setLore(lore);
				itmStack19.setItemMeta(TP);
				itmStack19.setDurability(red);
				inv.setItem(19, itmStack19);
			} else {
				ItemMeta TP = itmStack19.getItemMeta();
				TP.setDisplayName("TP");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				TP.setLore(lore);
				itmStack19.setItemMeta(TP);
				itmStack19.setDurability(green);
				inv.setItem(19, itmStack19);
			}
			player.openInventory(inv);
			return true;
		}
		return false;
	}
}
