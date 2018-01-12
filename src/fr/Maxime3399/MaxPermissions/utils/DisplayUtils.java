package fr.Maxime3399.MaxPermissions.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class DisplayUtils {
	
	public static void load(Player p){
		
		if(!DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "prefix").equalsIgnoreCase("")){
			
			define(p, DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "prefix"));
			
		}else{
			
			if(!DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "group").equalsIgnoreCase("")){
				
				if(!DataUtils.getGroupStringInfo(DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "group"), "prefix").equalsIgnoreCase("")){
					
					define(p, DataUtils.getGroupStringInfo(DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "group"), "prefix"));
					
				}
				
			}
			
		}
		
	}
	
	private static Scoreboard s = Bukkit.getScoreboardManager().getMainScoreboard();
	private static Team t = null;
	
	@SuppressWarnings("deprecation")
	private static void define(Player p, String display){
		
		if(p.getDisplayName().equalsIgnoreCase(p.getName())){
			
			p.setDisplayName(display.replaceAll("&", "§")+p.getName()+"§r");
			
			if(s.getTeam(p.getName()) == null){
				t = s.registerNewTeam(p.getName());
			}else{
				t = s.getTeam(p.getName());
			}
			t.setPrefix(display.replaceAll("&", "§"));
			t.addPlayer(p);
			
			for(Player pls : Bukkit.getOnlinePlayers()){
				pls.setScoreboard(s);
			}
			
		}
		
	}

}
