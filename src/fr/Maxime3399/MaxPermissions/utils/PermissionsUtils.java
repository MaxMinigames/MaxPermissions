package fr.Maxime3399.MaxPermissions.utils;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import fr.Maxime3399.MaxPermissions.MainClass;

public class PermissionsUtils {
	
	public static void loadPermissions(Player p){
		
		boolean op = false;
		p.getEffectivePermissions().removeAll(p.getEffectivePermissions());
		PermissionAttachment attachment = p.addAttachment(MainClass.getInstance());
		String group = DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "group");
		String perms = DataUtils.getPlayerStringInfo(p.getUniqueId().toString(), "perms");
		
		if(!group.equalsIgnoreCase("")){
			
			String perm[] = DataUtils.getGroupStringInfo(group, "perms").split(",");
			
			for(String s : perm){
				
				if(s.equalsIgnoreCase("*")) {
					op = true;
				}
				attachment.setPermission(s, true);
				
			}
			
		}
		
		if(!perms.equalsIgnoreCase("")){
			
			String perm[] = perms.split(",");
			
			for(String s : perm){
				
				if(s.equalsIgnoreCase("*")) {
					op = true;
				}
				attachment.setPermission(s, true);
				
			}
			
		}
		
		p.setOp(op);
		
	}

}
