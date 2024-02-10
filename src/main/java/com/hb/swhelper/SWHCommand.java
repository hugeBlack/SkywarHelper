package com.hb.swhelper;

import com.walrusone.skywarsreloaded.menus.gameoptions.objects.GameKit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class SWHCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(args.length==0) return false;
        if(args[0].equals("rkit")) {
            if(args.length<2){
                sender.sendMessage("§c/swh rkit <0|1|2|3> 0: disable kit; 1: player can choose kit; 2: each player gets different random kits; 3: each player gets same kit§r");
                return false;
            }

            switch (args[1]) {
                case "0":
                    SWHelper.randomKitMode = 0;
                    sender.sendMessage("Random Kit:§a disable kit§r");
                    break;
                case "1":
                    SWHelper.randomKitMode = 1;
                    sender.sendMessage("Random Kit:§a player can choose kit§r");
                    break;
                case "2":
                    SWHelper.randomKitMode = 2;
                    sender.sendMessage("Random Kit:§a each player gets different random kits§l");
                    break;
                case "3":
                    SWHelper.randomKitMode = 3;
                    sender.sendMessage("Random Kit:§a each player gets same kit§l");
                    break;
                default:
                    sender.sendMessage("§c/swh rkit <0|1|2|3> 0: disable kit; 1: player can choose kit; 2: each player gets different random kits; 3: each player gets same kit§r");
                    break;
            }

            return true;
        }else if(args[0].equals("resetWin")) {
            ArrayList<GameKit> kits = GameKit.getKits();
            for(GameKit kit : kits){
                kit.setWinCount(0);
                kit.setUseCount(0);
                GameKit.saveKit(kit);
            }

            return true;
        }else{
            return false;
        }
    }
}