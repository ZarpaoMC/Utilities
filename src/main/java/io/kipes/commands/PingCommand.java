package io.kipes.commands;

import io.kipes.utils.PingUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class PingCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (arguments.length > 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUsage: /" + label + " <playerName>"));
            return true;
        }

        if (arguments.length == 0) {
            if (sender instanceof Player) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + sender.getName() + "'s ping is " + PingUtil.getPing((Player) sender)) + "ms.");
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou can not execute this command on console."));
            }
        }

        if (arguments.length == 1) {
            Player target = Bukkit.getServer().getPlayerExact(arguments[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlayer named '" + arguments[0] + "' not found."));
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + target.getName() + "'s ping is " + PingUtil.getPing(target) + "ms."));
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] arguments) {
        if (arguments.length > 1) {
            return Collections.emptyList();
        }

        return null;
    }

}