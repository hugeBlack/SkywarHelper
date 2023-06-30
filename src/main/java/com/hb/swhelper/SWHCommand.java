package com.hb.swhelper;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SWHCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(args.length==0) return false;
        if(args[0].equals("rkit")) {
            if(args.length<2){
                sender.sendMessage("§c/swh rkit <true|false>§r");
                return false;
            }

            if(args[1].equals("false")) {
                SWHelper.randomKitEnabled = false;
                sender.sendMessage("Random Kit: §cDISABLED§r");
            }
            else if(args[1].equals("true")){
                SWHelper.randomKitEnabled = true;
                sender.sendMessage("Random Kit: §aENABLED§l");
            }else{
                sender.sendMessage("§c/swh rkit <true|false>§r");
            }

            return true;
        }else{
            return false;
        }
    }
}