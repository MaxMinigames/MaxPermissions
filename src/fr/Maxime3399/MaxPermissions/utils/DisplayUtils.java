package fr.Maxime3399.MaxPermissions.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import fr.Maxime3399.MaxPermissions.MainClass;

public class DisplayUtils {
	
	public static void load(Player p){
		
		if(!DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "prefix").equalsIgnoreCase("")){
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					
					define(p, DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "prefix"));
					
				}
				
			}, 2);
			
		}else{
			
			if(!DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "group").equalsIgnoreCase("")){
				
				if(!DataUtils.getGroupStringInfo(DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "group"), "prefix").equalsIgnoreCase("")){
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							
							define(p, DataUtils.getGroupStringInfo(DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "group"), "prefix"));
							
						}
						
					}, 2);
					
				}
				
			}
			
		}
		
	}
	
	private static Scoreboard s = Bukkit.getScoreboardManager().getMainScoreboard();
	private static Team t = null;
	
	@SuppressWarnings("deprecation")
	private static void define(Player p, String display){
		
		if(p.getDisplayName().equalsIgnoreCase(p.getName())){
			
			String prio = display.substring(0, 5);
			display = display.replaceAll(prio, "");
			p.setDisplayName(display.replaceAll("&", "§")+p.getName()+"§r");
			
			if(s.getTeam(prio+p.getName().substring(p.getName().length()-1)) == null){
				t = s.registerNewTeam(prio+p.getName().substring(p.getName().length()-1));
			}else{
				t = s.getTeam(prio+p.getName().substring(p.getName().length()-1));
			}
			t.setPrefix(display.replaceAll("&", "§"));
			t.addPlayer(p);
			
			for(Player pls : Bukkit.getOnlinePlayers()){
				pls.setScoreboard(s);
			}
			
		}
		
	}

}
