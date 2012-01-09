package me.freeplaynz.instamode;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class InstaMode extends JavaPlugin {	
	String nomode;
	String already_creative;
	String already_survival;
	String info_creative, info_survival;
    @Override
    public void onDisable() {
    	this.saveConfig();
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " v" + pdfFile.getVersion() + " is disabled!");
    }

    @Override
    public void onEnable() {
    	if (this.getConfig().isSet("error.nomode")==false){
	    	this.getConfig().set("error.nomode", "You didn't give us a game mode!");
    	}
    	if (this.getConfig().isSet("error.creative")==false){
	    	this.getConfig().set("error.creative", "You're already in Creative mode!");
    	}
    	if (this.getConfig().isSet("error.survival")==false){
	    	this.getConfig().set("error.survival", "You're already in Survival mode!");
    	}
    	if (this.getConfig().isSet("info.creative")==false){
	    	this.getConfig().set("info.creative", "Set to Creative Mode!");
    	}
    	if (this.getConfig().isSet("info.survival")==false){
	    	this.getConfig().set("info.survival", "Set to Survival Mode!");
    	}
    	nomode = this.getConfig().getString("error.nomode", "You didn't give us a game mode!");
    	already_creative = this.getConfig().getString("error.creative", "You're already in Creative mode!");
    	already_survival = this.getConfig().getString("error.survival", "You're already in Survival mode!");
    	info_creative = this.getConfig().getString("info.creative", "Set to Creative Mode!");
    	info_survival = this.getConfig().getString("info.survival", "Set to Survival Mode!");
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " v" + pdfFile.getVersion() + " is enabled!");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player)sender;
        
        if (commandLabel.equals("mode")) {
            if (args.length < 0) {
                player.sendMessage(ChatColor.RED + nomode);
                return true;
            } else if (args.length == 1) {
            	if ((args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")) && (player.hasPermission("*") || player.hasPermission("instamode.*") || player.hasPermission("instamode.survival"))) {
                    if (player.getGameMode() == GameMode.SURVIVAL) {
            		player.sendMessage(ChatColor.RED + already_survival);
            		return true;
                    } else {
            		player.setGameMode(GameMode.SURVIVAL);
            		player.sendMessage(ChatColor.GREEN + info_survival);
            		return true;
                    }
            	} else if ((args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) && (player.hasPermission("*") || player.hasPermission("instamode.*") || player.hasPermission("instamode.creative"))) {
                    if (player.getGameMode() == GameMode.CREATIVE) {
            		player.sendMessage(ChatColor.RED + already_creative);
            		return true;
                    } else {
            		player.setGameMode(GameMode.CREATIVE);
            		player.sendMessage(ChatColor.GREEN + info_creative);
            		return true;
                    }
            	}
            }
        }
        return false;
    }
}
