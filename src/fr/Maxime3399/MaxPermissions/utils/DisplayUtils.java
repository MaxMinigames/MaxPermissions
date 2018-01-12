package fr.Maxime3399.MaxPermissions.utils;

import org.bukkit.entity.Player;

public class DisplayUtils {
	
	public static void load(Player p){
		
		if(!DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "display").equalsIgnoreCase("")){
			
			define(p, DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "display").replaceAll("&", "§").replaceAll("%name%", p.getName()));
			
		}else{
			
			if(!DataUtils.getGroupStringInfo(DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "group"), "display").equalsIgnoreCase("")){
				
				define(p, DataUtils.getGroupStringInfo(DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "group"), "display").replaceAll("&", "§").replaceAll("%name%", p.getName()));
				
			}
			
		}
		
	}
	
	private static void define(Player p, String display){
		
		p.setDisplayName(display);
		p.setPlayerListName(display);
		
	}

}
