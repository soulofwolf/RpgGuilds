package com.vartala.soulofw0lf.rpgguilds;



import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuildHandler implements CommandExecutor {
	RpgGuilds Rpgg;
	public GuildHandler(RpgGuilds rpgg){
		this.Rpgg = rpgg;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if (args.length == 0){
			return false;
		}
		if (args[0].equalsIgnoreCase("create")){
			if (args.length != 3){
				player.sendMessage("Please use /guild create guildname tag");
				return true;
			}
			if (args[2].length() != 4){
				player.sendMessage("Guild tags must be 4 letters!");
				return true;
			}
			if (this.Rpgg.getConfig().contains("Guilds")){
				
				for (String guilds : this.Rpgg.getConfig().getConfigurationSection("Guilds").getKeys(false)){
					if (args[2].equalsIgnoreCase(this.Rpgg.getConfig().getString("Guilds." + guilds + ".Tag"))){
						player.sendMessage("This guild tag already exists!");
						return true;
					}
				}
			}
			if (!(player.hasPermission("guild.create"))){
				player.sendMessage("You do not have permission to use this command!");
				return true;
			}
			if (this.Rpgg.getConfig().contains(player.getName())){
				player.sendMessage("You are already in a guild! you can't create a new one!");
				return true;
			}
			if(this.Rpgg.getConfig().contains(args[1].replaceAll("_", " "))){
				player.sendMessage("A guild named " + args[1].replaceAll("_", " ") + " already exists!");
				return true;
			}
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Players." + player.getName() + ".Rank", "Leader");
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Leader", player.getName());
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Tag", args[2]);
			this.Rpgg.getConfig().set(player.getName() + ".Guild.Name", args[1].replaceAll("_", " "));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".DefTerm.Leader", "Leader");
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.Invite", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.Ochat", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.Kick", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.Gmotd", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.Disband", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.Gchat", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.Gbank", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.Addslot", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.Withdrawl", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.Deposit", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.GbRanks", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.RankSet", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.RankTitle", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.CreateRank", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.DeleteRank", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.PlayerInfo", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.RankPerms", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.PlayerNotes", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.PlayerNotesView", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.PlayerNotesSet", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.TP", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Leader.Title", "�4Guild Master");
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.Invite", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.Ochat", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.Kick", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.Gmotd", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.Disband", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.Gchat", true);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.Gbank", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.Addslot", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.Withdrawl", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.Deposit", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.GbRanks", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.RankSet", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.RankTitle", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.CreateRank", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.DeleteRank", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.PlayerInfo", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.RankPerms", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.PlayerNotes", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.PlayerNotesView", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.PlayerNotesSet", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.TP", false);
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".Ranks.Newbies.Title", "�2Newbies");
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ") + ".DefTerm.Default", "Newbies");
			this.Rpgg.saveConfig();
			player.sendMessage("Congratulations " + player.getName() + " you are now the leader of the newly created guild " + args[1].replaceAll("_", " "));
			return true;
		}
		if (args[0].equalsIgnoreCase("invite")){
			if (args.length != 2){
				player.sendMessage("Improper usage of the guild invite command, please just use /guild invite playername");
				return true;
			}
			if (Bukkit.getPlayer(args[1]) ==  null){
				player.sendMessage("This player cannot be found");
				return true;
			}
			Player p = Bukkit.getPlayer(args[1]);
			String pi = p.getName();
			String guildn = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + player.getName() + ".Rank");
			if (!(this.Rpgg.getConfig().contains(player.getName()))){
				player.sendMessage("You are not even in a guild? how can you invite someone?");
				return true;
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + grank + ".Invite") == false){
				player.sendMessage("You do not have permission to invite people to this guild!");
				return true;
			}
			if (this.Rpgg.getConfig().contains(pi)){
				player.sendMessage(pi + " is already in a guild!");
				return true;
			}
			if (this.Rpgg.getConfig().contains("Pending." + pi)){
				player.sendMessage("This player already has a pending guild invite from " + this.Rpgg.getConfig().getString("Pending." + pi + ".Guild"));
				return true;
			}
			this.Rpgg.getConfig().set("Pending." + pi + ".Guild", guildn);
			this.Rpgg.saveConfig();
			player.sendMessage("You have invited " + pi + " To join " + guildn);
			p.sendMessage("You have a pending guild invite from '" + guildn + "' type </guild accept> to join this guild. or </guild deny> to turn it down.");
			return true;
		}
		if (args[0].equalsIgnoreCase("accept")){
			if (!(this.Rpgg.getConfig().contains("Pending." + player.getName()))){
				player.sendMessage("You do not have any pending guild invites!");
				return true;
			}

			String guildn = this.Rpgg.getConfig().getString("Pending." + player.getName() + ".Guild");
			if (!(this.Rpgg.getConfig().contains("Guilds." + guildn))){
				player.sendMessage("This guild no longer exists!");
				this.Rpgg.getConfig().set("Pending." + player.getName(), null);
				return true;
			}
			String newbies = this.Rpgg.getConfig().getString("Guilds." + guildn + ".DefTerm.Default");
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Players." + player.getName() + ".Rank", newbies);
			this.Rpgg.getConfig().set(player.getName() + ".Guild.Name", guildn);
			this.Rpgg.getConfig().set("Pending." + player.getName(), null);
			this.Rpgg.saveConfig();
			if (this.Rpgg.getConfig().getBoolean("Chat") == true){
				player.setDisplayName("�F[�2" + guildn + "�f]" + player.getDisplayName());
			}
			for (String key : this.Rpgg.getConfig().getConfigurationSection("Guilds." + guildn + ".Players").getKeys(false)){
				if (Bukkit.getPlayer(key) == null){
				} else {
					if (Bukkit.getPlayer(key).getName() == player.getName()){
						player.sendMessage("You Have Joined  " + guildn + "!!!");
					} else {
						Player p = Bukkit.getPlayer(key);
						p.sendMessage("�3" + player.getName() + "�2 Has Joined The Guild!");
					}
				}
			}
			return true;
		}
		if (args[0].equalsIgnoreCase("deny")){
			if (!(this.Rpgg.getConfig().contains("Pending." + player.getName()))){
				player.sendMessage("You do not have any pending guild invites!");
				return true;
			}
			String guildn = this.Rpgg.getConfig().getString("Pending." + player.getName() + ".Guild");
			String lead = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Leader");
			if (Bukkit.getPlayer(lead) == null){
			} else {
				Player gleader = Bukkit.getPlayer(lead);
				gleader.sendMessage(player.getName() + " has declined your guild invite.");
			}
			player.sendMessage("You have declined joining " + guildn + ".");
			this.Rpgg.getConfig().set("Pending." + player.getName(), null);
			this.Rpgg.saveConfig();
			return true;
		}		
		if (args[0].equalsIgnoreCase("gmotd")){
			StringBuilder buffer = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				buffer.append(' ').append(args[i]);
			}
			String s = buffer.toString();
			s = s.replaceAll("&", "�");
			String guildn = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + player.getName() + ".Rank");
			if (!(this.Rpgg.getConfig().contains(player.getName()))){
				player.sendMessage("You are not even in a guild? how can you set the Gmotd?");
				return true;
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + grank + ".Gmotd") == false){
				player.sendMessage("You do not have permission to set the Gmotd!");
				return true;
			}
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Gmotd", s);
			this.Rpgg.saveConfig();
			player.sendMessage("You have saved the gmotd as �2" + s);
			return true;
		}
		if (args[0].equalsIgnoreCase("Kick")){
			String guildn = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + player.getName() + ".Rank");
			if (args.length != 2){
				player.sendMessage("Please include the name of the person you want to kick.");
				return true;
			}
			if (!(this.Rpgg.getConfig().contains(player.getName()))){
				player.sendMessage("You are not even in a guild? How can you kick someone?");
				return true;
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + grank + ".Kick") == false){
				player.sendMessage("You do not have permission to kick someone from the guild!");
				return true;
			}
			if (!(this.Rpgg.getConfig().contains("Guilds." + guildn + ".Players." + Bukkit.getPlayer(args[1]).getName()))){
				player.sendMessage("This player is not a member of your guild!");
				return true;
			}
			String prank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + args[1] + ".Rank");
			if (prank.equalsIgnoreCase("Leader")){
				player.sendMessage("You cannot kick a guild leader!");
				return true;
			}
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Players." + args[1], null);
			this.Rpgg.getConfig().set(args[1], null);
			if (Bukkit.getPlayer(args[1]) != null){
				Player p = Bukkit.getPlayer(args[1]);
				p.sendMessage("You have been removed from " + guildn + ".");
				p.setDisplayName(p.getName());
			}
			player.sendMessage("You have removed " + args[1] + " from " + guildn + ".");
			this.Rpgg.saveConfig();
			return true;
		}
		if (args[0].equalsIgnoreCase("disband")){
			String guildn = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + player.getName() + ".Rank");
			if (!(this.Rpgg.getConfig().contains(player.getName()))){
				player.sendMessage("You are not even in a guild? How can you disband it?");
				return true;
			}
			if (this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks." + grank + ".Disband") == false){
				player.sendMessage("You do not have permission to disband the guild!");
				return true;
			}
			for (String key : this.Rpgg.getConfig().getConfigurationSection("Guilds." + guildn + ".Players").getKeys(false)){
				this.Rpgg.getConfig().set(key, null);

				if (Bukkit.getPlayer(key) == null){
				} else {
					if (Bukkit.getPlayer(key).getName() == player.getName()){
						player.sendMessage("�4You Have Disbanded  " + guildn + "!!!");
						player.setDisplayName(player.getName());
					} else {
						Player p = Bukkit.getPlayer(key);
						p.setDisplayName(p.getName());
						p.sendMessage("�3" + player.getName() + "�4 Has Disbanded The Guild!");
					}
				}
			}
			this.Rpgg.getConfig().set("Guilds." + guildn, null);
			this.Rpgg.saveConfig();
			return true;
		}
		if (args[0].equalsIgnoreCase("quit")){
			String guildn = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
			if (!(this.Rpgg.getConfig().contains(player.getName()))){
				player.sendMessage("You are not even in a guild? How can you quit?");
				return true;
			}
			for (String key : this.Rpgg.getConfig().getConfigurationSection("Guilds." + guildn + ".Players").getKeys(false)){
                if (Bukkit.getPlayer(key) == null){
                    continue;
                } else {
				if (Bukkit.getPlayer(key).getName() == player.getName()){
					player.sendMessage("�4You Have Left  " + guildn + "!!!");
				} else {

						Player p = Bukkit.getPlayer(key);
						p.sendMessage("�3" + player.getName() + "�4 Has Quit The Guild!");
					}
				}
			}
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Players." + player.getName(), null);
			this.Rpgg.getConfig().set(player.getName(), null);
			player.setDisplayName(player.getName());
			return true;
		}
		if (args[0].equalsIgnoreCase("List")){
			if (args.length != 1){
				player.sendMessage("Improper usage! Please use /guild list");
				return true;
			}
			String guildn = this.Rpgg.getConfig().getString(player.getName() + ".Guild.Name");
			if (!(this.Rpgg.getConfig().contains(player.getName()))){
				player.sendMessage("You are not even in a guild? how can you expect to list online members?");
				return true;
			}
			for (String key : this.Rpgg.getConfig().getConfigurationSection("Guilds." + guildn + ".Players").getKeys(false)){
				if (Bukkit.getPlayer(key) != null){
					Player p = Bukkit.getPlayer(key);
					String name = p.getName();
					String rank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + name + ".Rank");
					String title = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Ranks." + rank + ".Title");
					player.sendMessage("�F[" + title + "�F]" + " " + name + " - Status �2Online\n");
				} else {
					String rank = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Players." + key + ".Rank");
					String title = this.Rpgg.getConfig().getString("Guilds." + guildn + ".Ranks." + rank + ".Title");
					player.sendMessage("�F[" + title + "�F]" + " " + key + " - Status �8Offline\n");				
				}
			}

			player.sendMessage("List complete");
			return true;
		}
		if (args[0].equalsIgnoreCase("tag")){
			if ((!player.isOp())){
				player.sendMessage("Only ops can use this command!");
				return true;
			}
			Boolean tag = this.Rpgg.getConfig().getBoolean("Guild Names in Chat");
			if (tag == true){
				this.Rpgg.getConfig().set("Guild Names in Chat", false);
				player.sendMessage("Guild tags disabled.");
			} else {
				this.Rpgg.getConfig().set("Guild Names in Chat", true);
				player.sendMessage("Guild tags enabled.");
			}
			this.Rpgg.saveConfig();
			return true;
		}
		if (args[0].equalsIgnoreCase("TP")){
			if (!(player.isOp())){
				player.sendMessage("Only ops can change the default server teleport behavior!");
				return true;
			}
			if (args.length != 2){
				player.sendMessage("wrong usage! please use /guild tp on  or guild tp off!");
				return true;
			}
			if (args[1].equalsIgnoreCase("on")){
				this.Rpgg.getConfig().set("TP", true);
				player.sendMessage("Teleporting guild members is now allowed on your server!");
				return true;
			}
			if (args[1].equalsIgnoreCase("off")){
				this.Rpgg.getConfig().set("TP", false);
				player.sendMessage("Teleporting guild members is no longer allowed on your server!");
				return true;
			}
		}

		return false;
	}
}