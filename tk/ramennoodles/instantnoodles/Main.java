package tk.ramennoodles.instantnoodles;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import tk.ramennoodles.instantnoodles.adderall.SignPlaceListener;
import tk.ramennoodles.instantnoodles.events.ChatListener;
import tk.ramennoodles.instantnoodles.events.MobSpawnListener;
import tk.ramennoodles.instantnoodles.events.SleepListener;
import tk.ramennoodles.instantnoodles.events.TablistJoinListener;
import tk.ramennoodles.instantnoodles.events.WeaponCooldownListener;
import tk.ramennoodles.instantnoodles.events.VoteListener;

public class Main extends JavaPlugin {
	
	public static FileConfiguration config;
	
	public static JavaPlugin pluginReference;
	
	Commands commandManager = new Commands();
	
	@Override
	public void onEnable() {
		pluginReference = this;
		
		Recipes.createRecipes();
		
		getLogger().info("InstantNoodles loaded");
		
		Bukkit.getPluginManager().registerEvents(new SignPlaceListener(), this);
		Bukkit.getPluginManager().registerEvents(new SleepListener(), this);
		Bukkit.getPluginManager().registerEvents(new Recipes(), this);
		Bukkit.getPluginManager().registerEvents(new MobSpawnListener(), this);
		Bukkit.getPluginManager().registerEvents(new WeaponCooldownListener(), this);
		Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
		Bukkit.getPluginManager().registerEvents(new VoteListener(), this);
		Bukkit.getPluginManager().registerEvents(new TablistJoinListener(), this);
		
		
		getCommand("discord").setExecutor(commandManager);
		getCommand("apply").setExecutor(commandManager);
		getCommand("vote").setExecutor(commandManager);
		getCommand("discord").setExecutor(commandManager);
		getCommand("clearchat").setExecutor(commandManager);
		getCommand("server").setExecutor(commandManager);
		getCommand("local").setExecutor(commandManager);
		
		this.saveDefaultConfig();
		
		config = this.getConfig();
		
		
		int broadcastNumber = config.getList("broadcasts.messages").size();
		
		getServer().getScheduler().runTaskTimer(this, new Runnable() {
			
			int tracker = 0;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String broadcast = ChatColor.translateAlternateColorCodes('&', config.getStringList("broadcasts.messages").get(tracker));
				String broadcaster = ChatColor.translateAlternateColorCodes('&', config.getString("broadcasts.broadcaster"));
				
				Bukkit.broadcastMessage(broadcaster + ChatColor.DARK_GRAY+"» "+ChatColor.RESET + broadcast);
				
				
				tracker+=1;
				if (tracker >= broadcastNumber) { tracker = 0;}
			}
			
		}, 0, config.getInt("broadcasts.delay")*20);
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
