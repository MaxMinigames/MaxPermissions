package fr.Maxime3399.MaxPermissions.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.Maxime3399.MaxPermissions.utils.DataUtils;
import fr.Maxime3399.MaxPermissions.utils.DisplayUtils;
import fr.Maxime3399.MaxPermissions.utils.PermissionsUtils;

public class PlayerJoin implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		
		Player p = e.getPlayer();
		DataUtils.registerPlayer(p);
		
		if(!DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "group").equalsIgnoreCase("")){
			
			String group = DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "group");
			
			boolean exist = false;
			
			for(String sz : DataUtils.getGroups()){
				
				if(group.equalsIgnoreCase(sz)){
					
					exist = true;
					
				}
				
			}
			
			if(!exist){
				
				DataUtils.setPlayerStringInfo(p.getUniqueId().toString(), "group", "");
				
			}
			
		}
		
		PermissionsUtils.loadPermissions(p);
		DisplayUtils.load(p);
		
	}

}
