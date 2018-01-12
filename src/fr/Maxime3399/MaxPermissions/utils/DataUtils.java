package fr.Maxime3399.MaxPermissions.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.bukkit.entity.Player;

import fr.Maxime3399.MaxPermissions.MainClass;

public class DataUtils {
	
	private static String database = MainClass.getInstance().getConfig().getString("Database");
	
	public static void registerPlugin(){
		
		try{
			MySQLUtils.state.executeUpdate("CREATE TABLE IF NOT EXISTS `"+database+"`.`maxpermissions_groups` ( `name` VARCHAR(255) NOT NULL , `info-prefix` VARCHAR(255) NOT NULL , `info-perms` VARCHAR(255) NOT NULL , PRIMARY KEY (`name`)) ENGINE = MyISAM;");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		try{
			MySQLUtils.state.executeUpdate("CREATE TABLE IF NOT EXISTS `"+database+"`.`maxpermissions_players` ( `uuid` VARCHAR(255) NOT NULL , `info-name` VARCHAR(255) NOT NULL , `info-prefix` VARCHAR(255) NOT NULL , `info-group` VARCHAR(255) NOT NULL , `info-perms` VARCHAR(255) NOT NULL , PRIMARY KEY (`uuid`)) ENGINE = MyISAM;");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public static void registerPlayer(Player p){
		
		try{
			MySQLUtils.state.executeUpdate("INSERT INTO `maxpermissions_players` (`uuid`, `info-name`, `info-display`, `info-groups`, `info-perms`) VALUES ('"+p.getUniqueId().toString()+"', '"+p.getName()+"', '', '', '');");
		}catch(SQLException e){

		}
		
	}
	
	public static String getPlayerStringInfo(String uuid, String info){
		
		String result = null;
		
		try{
			ResultSet r = MySQLUtils.state.executeQuery("SELECT * FROM `"+database+"`.`maxpermissions_players` WHERE uuid = '"+uuid+"'");
			r.next();
			result = r.getString("info-"+info);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static int getPlayerIntInfo(String uuid, String info){
		
		int result = 0;
		
		try{
			ResultSet r = MySQLUtils.state.executeQuery("SELECT * FROM `"+database+"`.`maxpermissions_players` WHERE uuid = '"+uuid+"'");
			r.next();
			result = r.getInt("info-"+info);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static void setPlayerStringInfo(String uuid, String info, String value){
		
		try{
			MySQLUtils.state.executeUpdate("UPDATE `"+database+"`.`maxpermissions_players` SET `info-"+info+"` = '"+value+"' WHERE `maxpermissions_players`.`uuid` = '"+uuid+"';");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public static void setPlayerIntInfo(String uuid, String info, int value){
		
		try{
			MySQLUtils.state.executeUpdate("UPDATE `"+database+"`.`maxpermissions_players` SET `info-"+info+"` = '"+value+"' WHERE `maxpermissions_players`.`uuid` = '"+uuid+"';");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public static String getGroupStringInfo(String name, String info){
		
		String result = null;
		
		try{
			ResultSet r = MySQLUtils.state.executeQuery("SELECT * FROM `"+database+"`.`maxpermissions_groups` WHERE name = '"+name+"'");
			r.next();
			result = r.getString("info-"+info);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static int getGroupIntInfo(String name, String info){
		
		int result = 0;
		
		try{
			ResultSet r = MySQLUtils.state.executeQuery("SELECT * FROM `"+database+"`.`maxpermissions_players` WHERE name = '"+name+"'");
			r.next();
			result = r.getInt("info-"+info);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static void setGroupStringInfo(String name, String info, String value){
		
		try{
			MySQLUtils.state.executeUpdate("UPDATE `"+database+"`.`maxpermissions_groups` SET `info-"+info+"` = '"+value+"' WHERE `maxpermissions_players`.`name` = '"+name+"';");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public static void setGroupIntInfo(String name, String info, int value){
		
		try{
			MySQLUtils.state.executeUpdate("UPDATE `"+database+"`.`maxpermissions_players` SET `info-"+info+"` = '"+value+"' WHERE `maxpermissions_players`.`name` = '"+name+"';");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<String> getGroups(){
	    ArrayList<String> expenses = new ArrayList<String>();
	    try {
	        Statement stmt = MySQLUtils.state;
	        ResultSet result = stmt.executeQuery("SELECT * FROM `maxpermissions_groups`");
	        while(result.next()){
	            expenses.add(result.getString(1));
	        }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return expenses;
	}
	
	public static ArrayList<String> getPlayers(){
	    ArrayList<String> expenses = new ArrayList<String>();
	    try {
	        Statement stmt = MySQLUtils.state;
	        ResultSet result = stmt.executeQuery("SELECT * FROM `maxpermissions_players`");
	        while(result.next()){
	            expenses.add(result.getString(1));
	        }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return expenses;
	}

}
