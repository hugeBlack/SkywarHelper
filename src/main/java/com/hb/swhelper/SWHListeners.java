package com.hb.swhelper;

import com.walrusone.skywarsreloaded.enums.MatchState;
import com.walrusone.skywarsreloaded.game.GameMap;
import com.walrusone.skywarsreloaded.menus.gameoptions.objects.GameKit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.walrusone.skywarsreloaded.events.SkyWarsMatchStateChangeEvent;

import java.util.ArrayList;
import java.util.Random;

public class SWHListeners implements Listener {
    private final Random rand = new Random();
    @EventHandler
    public void onMatchStart(SkyWarsMatchStateChangeEvent e) throws Exception {

        if(e.getState()!= MatchState.PLAYING) return;

        ArrayList<Player> playerList = e.getGameMap().getAlivePlayers();
        GameMap gameMap = e.getGameMap();
        ArrayList<GameKit> availableKits = null;
        if(SWHelper.randomKitEnabled){
            availableKits = new ArrayList<>();
            ArrayList<GameKit> gameKits = GameKit.getKits();
            for(GameKit k:gameKits){
                if(k.getEnabled()) availableKits.add(k);
            }
            if(availableKits.size()==0){
                SWHelper.randomKitEnabled = false;
                for(Player p :playerList) p.sendMessage("§cRandom kits disabled since there isn't any enabled kit!");
            }
        }

        for(Player p :playerList){
            if(SWHelper.randomKitEnabled) giveRandomKit(p,gameMap,availableKits);

            GameKit k = gameMap.getSelectedKit(p);
            if(k==null) continue;
            if(k.getName().equals("enchanter")){
                p.setLevel(100);
            }
        }
    }

    private void giveRandomKit(Player player,GameMap map, ArrayList<GameKit> kits){
        GameKit kit = kits.get(rand.nextInt(kits.size()));
        map.setKitVote(player,kit);
        player.sendMessage("You got random kit '§e"+kit.getName()+"§r'!");
    }
}
