package net.pwncraft.kaikz.instamode;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class InstaMode extends JavaPlugin {	
    @Override
    public void onDisable() {
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " v" + pdfFile.getVersion() + " is disabled!");
    }

    @Override
    public void onEnable() {
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
                player.sendMessage(ChatColor.RED + "You didn't give us a game mode!");
                return true;
            } else if (args.length == 1) {
            	if ((args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")) && (player.hasPermission("*") || player.hasPermission("instamode.*") || player.hasPermission("instamode.survival"))) {
                    if (player.getGameMode() == GameMode.SURVIVAL) {
            		player.sendMessage(ChatColor.RED + "You're already in Survival mode!");
            		return true;
                    } else {
            		player.setGameMode(GameMode.SURVIVAL);
            		player.sendMessage(ChatColor.GREEN + "Set to Survival Mode!");
            		return true;
                    }
            	} else if ((args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) && (player.hasPermission("*") || player.hasPermission("instamode.*") || player.hasPermission("instamode.creative"))) {
                    if (player.getGameMode() == GameMode.CREATIVE) {
            		player.sendMessage(ChatColor.RED + "You're already in Creative mode!");
            		return true;
                    } else {
            		player.setGameMode(GameMode.CREATIVE);
            		player.sendMessage(ChatColor.GREEN + "Set to Creative Mode!");
            		return true;
                    }
            	}
            }
        }
        return false;
    }
}
