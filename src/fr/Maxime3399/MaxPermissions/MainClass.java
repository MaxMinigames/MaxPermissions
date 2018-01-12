package fr.Maxime3399.MaxPermissions;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.Maxime3399.MaxPermissions.events.EventsManager;
import fr.Maxime3399.MaxPermissions.utils.DataUtils;
import fr.Maxime3399.MaxPermissions.utils.MySQLUtils;

public class MainClass extends JavaPlugin{
	
	private static Plugin plugin;
	
	public void onEnable(){
		
		plugin = this;
		File f = new File(getDataFolder(), "config.yml");
		if(!f.exists()){
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
			Bukkit.getConsoleSender().sendMessage("§eMaxPermissions §d: §aThe configuration file has been created ! You have to configure the plugin with the file \"config.yml\".");
		}
		MySQLUtils.connect();
		
		if(MySQLUtils.state == null){
			
			Bukkit.getConsoleSender().sendMessage("§eMaxPermissions §d: §cAn error occurred while connecting to the database ! Please check the \"config.yml\" file.");
			Bukkit.getPluginManager().disablePlugin(this);
			
		}else{
			
			DataUtils.registerPlugin();
			EventsManager.registerEvent(this);
			
		}
		
	}
	
	public static Plugin getInstance(){
		
		return plugin;
		
	}

}
