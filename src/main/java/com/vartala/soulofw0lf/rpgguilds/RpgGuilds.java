package com.vartala.soulofw0lf.rpgguilds;



import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.scene.layout.Priority;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


import com.sk89q.worldguard.bukkit.WorldGuardPlugin;





public class RpgGuilds extends JavaPlugin implements Listener {
	static RpgGuilds plugin;
	public static List<String> gbanks = new ArrayList<>();
	public static WorldGuardPlugin wgPlugin = null;
	private static final Logger log = Logger.getLogger("Minecraft");
	public static Economy econ = null;
	@Override
	public void onEnable(){
		plugin = this;
		getCommand("guild").setExecutor(new GuildHandler(this));
		//done
		getCommand("g").setExecutor(new gcHandler(this));
		getCommand("o").setExecutor(new oHandler(this));
		getCommand("gtp").setExecutor(new gTP(this));
		getCommand("guilds").setExecutor(new guildslist(this));
		getCommand("lookup").setExecutor(new lookUpCommand(this));
		getCommand("hq").setExecutor(new hqCommand(this));
		//done without editable permissions
		getCommand("grank").setExecutor(new gRankHandler(this));
		Bukkit.getPluginManager().registerEvents(this, this);
		getLogger().info("RpgGuilds has been enabled!");
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
		if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard")){
			wgPlugin =  (WorldGuardPlugin)Bukkit.getPluginManager().getPlugin("WorldGuard");
			getLogger().info(ChatColor.RED + "[BankManager] Hooked Onto WorldGuard");
		}
		saveDefaultConfig();
		if (!(getConfig().contains("TP"))){
			getConfig().set("TP", false);
			saveConfig();
		}
		if (!(getConfig().contains("Guild Names in Chat"))){
			getConfig().set("Guild Names in Chat", true);
			saveConfig();
		}
		if (!(getConfig().contains("Chat"))){
			getConfig().set("Chat", true);
			saveConfig();
		}
		if (!(getConfig().contains("No Build"))){
			getConfig().set("No Build", true);
			saveConfig();
		}
		if (!setupEconomy() ) {
			log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
	}
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
	@Override
	public void onDisable(){
		getLogger().info("RpgGuilds has been Disabled!");
		saveConfig();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(label.equalsIgnoreCase("gbank")){
			Player player = (Player) sender;
			gbanks.add(player.getName());
			return BankCommands.bankCommands(sender, args);}
		return false;
	}





	@EventHandler
	public void guildBreak(BlockBreakEvent event){
		Player p = event.getPlayer();
		if (getConfig().getBoolean("No Build") == true){
			Location loc = p.getLocation();
			for (String guild : getConfig().getConfigurationSection("Guilds").getKeys(false)){
				if (getConfig().getConfigurationSection("Guilds." + guild + ".HQ") != null){
					double X1 = getConfig().getDouble("Guilds." + guild + ".HQ.X");
					double Y1 = getConfig().getDouble("Guilds." + guild + ".HQ.Y");
					double Z1 = getConfig().getDouble("Guilds." + guild + ".HQ.Z");
					String world1 = getConfig().getString("Guilds." + guild + ".HQ.World");
					if (p.getWorld().getName().equalsIgnoreCase(world1)){
						Location gloc = new Location(Bukkit.getWorld(world1), X1, Y1, Z1);
						if (loc.distance(gloc) <= 50){
							if (!(getConfig().contains(p.getName()))){
								p.sendMessage("You cannot build in the headquarters of " + guild + ".");
								event.setCancelled(true);
								return;
							}
							if(!(getConfig().getString(p.getName() + "Guild.Name").equalsIgnoreCase(guild))){
							p.sendMessage("You cannot build in the headquarters of " + guild + ".");
							event.setCancelled(true);
							return;
							}
						}
					}
				}
			}
		}
	}
	@EventHandler
	public void guildUse(PlayerBucketEmptyEvent event){
		Player p = event.getPlayer();
		if (getConfig().getBoolean("No Build") == true){
			Location loc = p.getLocation();
			for (String guild : getConfig().getConfigurationSection("Guilds").getKeys(false)){
				if (getConfig().getConfigurationSection("Guilds." + guild + ".HQ") != null){
					double X1 = getConfig().getDouble("Guilds." + guild + ".HQ.X");
					double Y1 = getConfig().getDouble("Guilds." + guild + ".HQ.Y");
					double Z1 = getConfig().getDouble("Guilds." + guild + ".HQ.Z");
					String world1 = getConfig().getString("Guilds." + guild + ".HQ.World");
					if (p.getWorld().getName().equalsIgnoreCase(world1)){
						Location gloc = new Location(Bukkit.getWorld(world1), X1, Y1, Z1);
						if (loc.distance(gloc) <= 50){
							if (!(getConfig().contains(p.getName()))){
								p.sendMessage("You cannot build in the headquarters of " + guild + ".");
								event.setCancelled(true);
								
								return;
							}
							if(!(getConfig().getString(p.getName() + "Guild.Name").equalsIgnoreCase(guild))){
							p.sendMessage("You cannot build in the headquarters of " + guild + ".");
							event.setCancelled(true);
							return;
							}
						}
					}
				}
			}
		}
	}
	@EventHandler
	public void guildUse(PlayerBucketFillEvent event){
		Player p = event.getPlayer();
		if (getConfig().getBoolean("No Build") == true){
			Location loc = p.getLocation();
			for (String guild : getConfig().getConfigurationSection("Guilds").getKeys(false)){
				if (getConfig().getConfigurationSection("Guilds." + guild + ".HQ") != null){
					double X1 = getConfig().getDouble("Guilds." + guild + ".HQ.X");
					double Y1 = getConfig().getDouble("Guilds." + guild + ".HQ.Y");
					double Z1 = getConfig().getDouble("Guilds." + guild + ".HQ.Z");
					String world1 = getConfig().getString("Guilds." + guild + ".HQ.World");
					if (p.getWorld().getName().equalsIgnoreCase(world1)){
						Location gloc = new Location(Bukkit.getWorld(world1), X1, Y1, Z1);
						if (loc.distance(gloc) <= 50){
							if (!(getConfig().contains(p.getName()))){
								p.sendMessage("You cannot build in the headquarters of " + guild + ".");
								event.setCancelled(true);
								return;
							}
							if(!(getConfig().getString(p.getName() + "Guild.Name").equalsIgnoreCase(guild))){
							p.sendMessage("You cannot build in the headquarters of " + guild + ".");
							event.setCancelled(true);
							return;
							}
						}
					}
				}
			}
		}
	}
	@EventHandler
	public void guildPlace(BlockPlaceEvent event){
		Player p = event.getPlayer();
		if (getConfig().getBoolean("No Build") == true){
			Location loc = p.getLocation();
			for (String guild : getConfig().getConfigurationSection("Guilds").getKeys(false)){
				if (getConfig().getConfigurationSection("Guilds." + guild + ".HQ") != null){
					double X1 = getConfig().getDouble("Guilds." + guild + ".HQ.X");
					double Y1 = getConfig().getDouble("Guilds." + guild + ".HQ.Y");
					double Z1 = getConfig().getDouble("Guilds." + guild + ".HQ.Z");
					String world1 = getConfig().getString("Guilds." + guild + ".HQ.World");
					if (p.getWorld().getName().equalsIgnoreCase(world1)){
						Location gloc = new Location(Bukkit.getWorld(world1), X1, Y1, Z1);
						if (loc.distance(gloc) <= 50){
							if (!(getConfig().contains(p.getName()))){
								p.sendMessage("You cannot build in the headquarters of " + guild + ".");
								event.setCancelled(true);
								return;
							}
							if(!(getConfig().getString(p.getName() + "Guild.Name").equalsIgnoreCase(guild))){
							p.sendMessage("You cannot build in the headquarters of " + guild + ".");
							event.setCancelled(true);
							return;
							}
						}
					}
				}
			}
		}
	}
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		Player p = event.getEntity();
		if (p.getKiller() instanceof Player){
			Player pl = p.getKiller();
			Integer kills = getConfig().getInt("Kills." + pl.getName());
			kills++;
			getConfig().set("Kills." + pl.getName(), kills);
		}

	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerClick(InventoryClickEvent event){
		Player player = (Player)event.getWhoClicked();
		if (getConfig().contains(player.getName())){
			String guildn = getConfig().getString(player.getName() + ".Guild.Name");
			if (event.getInventory().getTitle().equalsIgnoreCase("Ranks:")){
				ItemStack itm = event.getCurrentItem();
				if (itm != null){
					ItemMeta rim = itm.getItemMeta();
					if (rim != null){
						event.setCancelled(true);
						event.setResult(Result.DENY);
						final String rname = rim.getDisplayName();
						player.closeInventory();
						final Player p = player;
						new BukkitRunnable(){
							@Override
							public void run(){
								p.performCommand("grank edit " + rname);
								cancel();
								return;
							}
						}.runTaskLater(plugin, 8);
					}
				}
			}
			String rank = null;
			for (String key : getConfig().getConfigurationSection("Guilds." + guildn + ".Ranks").getKeys(false)){
				if (event.getInventory().getTitle().contains(key)){
					rank = key;
				}
			}
			if (rank != null){
				event.setCancelled(true);
				event.setResult(Result.DENY);
				ItemStack item = event.getCurrentItem();
				if(item == null || item.getTypeId() == 0){
					return;
				} else {
					ItemMeta im = event.getCurrentItem().getItemMeta();
					if (im.hasDisplayName()){
						String iname = im.getDisplayName();
						Short green = 5;
						Short red = 14;
						if (getConfig().getBoolean("Guilds." + guildn + ".Ranks." + rank + "." + iname) == false){
							getConfig().set("Guilds." + guildn + ".Ranks." + rank + "." + iname, true);
							ArrayList<String> lore = new ArrayList<String>();
							lore.add("True");
							im.setLore(lore);
							item.setItemMeta(im);
							item.setDurability(green);
							saveConfig();
						} else {
							getConfig().set("Guilds." + guildn + ".Ranks." + rank + "." + iname, false);
							ArrayList<String> lore = new ArrayList<String>();
							lore.add("False");
							im.setLore(lore);
							item.setItemMeta(im);
							item.setDurability(red);
							saveConfig();
						}
					}
				}
			}
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDmg(EntityDamageByEntityEvent event){
		if(event.getEntity() instanceof Player){
			Player a = (Player)event.getEntity();
			Player b = null;
			if(event.getDamager() instanceof Player){
				b = (Player)event.getDamager();
			}
			if(event.getDamager() instanceof Arrow){
				if (((Arrow)event.getDamager()).getShooter() instanceof Player){
					b = (Player)((Arrow)event.getDamager()).getShooter();
				}
			}
			if (b != null){
				if ((getConfig().contains(a.getName())) && (getConfig().contains(b.getName()))){ 
					String guild = getConfig().getString(a.getName() + ".Guild.Name");
					String guild1 = getConfig().getString(b.getName() + ".Guild.Name");
					if (guild.equalsIgnoreCase(guild1)){
						event.setDamage(0);
						event.setCancelled(true);
					}
				}
			}
		}
	}
	@EventHandler(priority = EventPriority.LOWEST)
	public void onChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		if (getConfig().getBoolean("Guild Names in Chat") == true){
			if (getConfig().contains(player.getName())){
				String guild = getConfig().getString(player.getName() + ".Guild.Name");
				String tag = getConfig().getString("Guilds." + guild + ".Tag");
				if (getConfig().getBoolean("Chat") == true){
					event.setMessage(ChatColor.WHITE + "["+ChatColor.GREEN +  tag + ChatColor.WHITE + "] " + event.getMessage());
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage(null);
		Integer newkills = 0;
		if (getConfig().getConfigurationSection("Kills." + player.getName()) == null){
			getConfig().set("Kills." + player.getName(), newkills);
		}
		if (getConfig().contains(player.getName())){
			String guild = getConfig().getString(player.getName() + ".Guild.Name");
			for (String key : getConfig().getConfigurationSection("Guilds." + guild + ".Players").getKeys(false)){
				if (Bukkit.getPlayer(key) != null){
					if (Bukkit.getPlayer(key).getName() == player.getName()){

						if (!(getConfig().getString("Guilds." + guild + ".Gmotd") == null)){
							player.sendMessage(getConfig().getString("Guilds." + guild + ".Gmotd"));
						} else {
							player.sendMessage("You are a part of " + guild);
						}
					} else {
						Player p = Bukkit.getPlayer(key);
						p.sendMessage("�3" + player.getName() + "�2 Has come online!");
					}
				}

			}
		}

	}
	@EventHandler
	public void onKick(PlayerKickEvent event2){
		event2.setLeaveMessage(null);
	}
	public void teleport(Player p, Player p2){
		final Player player = p;
		final Player player2 = p2;
		final Location loc = player.getLocation();
		final Location Loc = player2.getLocation();
		player.sendMessage("About to teleport, don't move!");
		player2.sendMessage("About to teleport, don't move!");
		new BukkitRunnable(){



			int count = 8;


			@Override
			public void run(){



				player.sendMessage( "Wait " + count + " Seconds." );
				player2.sendMessage( "Wait " + count + " Seconds." );
				count--;
				if (player.getLocation().getX() != loc.getX()){
					player.sendMessage("Cancelled teleport, don't move!");
					player2.sendMessage("Cancelled teleport, don't move!");
					cancel();
				}
				if (player.getLocation().getZ() != loc.getZ()){
					player.sendMessage("Cancelled teleport, don't move!");
					player2.sendMessage("Cancelled teleport, don't move!");
					cancel();
				}
				if (player2.getLocation().getX() != Loc.getX()){
					player.sendMessage("Cancelled teleport, don't move!");
					player2.sendMessage("Cancelled teleport, don't move!");
					cancel();
				}
				if (player2.getLocation().getZ() != Loc.getZ()){
					player.sendMessage("Cancelled teleport, don't move!");
					player2.sendMessage("Cancelled teleport, don't move!");
					cancel();
				}
				if (count == 0){
					player2.teleport(loc);
					cancel();
				}
			}

		}.runTaskTimer(plugin, 20, 20);
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent event1) {
		Player player = event1.getPlayer();
		event1.setQuitMessage(null);
		if (getConfig().contains(player.getName())){
			String guild = getConfig().getString(player.getName() + ".Guild.Name");
			for (String key : getConfig().getConfigurationSection("Guilds." + guild + ".Players").getKeys(false)){
				if (Bukkit.getPlayer(key) != null){
					Player p = Bukkit.getPlayer(key);
					p.sendMessage("�3" + player.getName() + "�2 Has gone offline!");

				}

			}
		}
	}
}