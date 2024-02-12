package com.hb.swhelper;

import com.walrusone.skywarsreloaded.enums.MatchState;
import com.walrusone.skywarsreloaded.game.GameMap;
import com.walrusone.skywarsreloaded.matchevents.MatchEvent;
import com.walrusone.skywarsreloaded.menus.gameoptions.objects.GameKit;
import org.bukkit.Difficulty;
import org.bukkit.GameEvent;
import org.bukkit.GameRule;
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

        Difficulty difficulty = e.getGameMap().getCurrentWorld().getDifficulty();
        if(difficulty != Difficulty.HARD) {
            e.getGameMap().getCurrentWorld().setDifficulty(Difficulty.HARD);
            // 如果世界不是困难，就设置为困难，并向所有玩家发送b
            for(Player p : e.getGameMap().getAllPlayers()){
                p.sendMessage("b");
            }
        }
        e.getGameMap().getCurrentWorld().setGameRule(GameRule.MOB_GRIEFING, true);
        /*
        0: disable kit;
        1: player can choose kit;
        2: each player gets different random kits;
        3: each player gets same kit
         */

        ArrayList<Player> playerList = e.getGameMap().getAlivePlayers();
        GameMap gameMap = e.getGameMap();
        ArrayList<GameKit> availableKits = null;
        if(SWHelper.randomKitMode > 1){
            availableKits = new ArrayList<>();
            ArrayList<GameKit> gameKits = GameKit.getKits();
            for(GameKit k:gameKits){
                if(k.getEnabled()) availableKits.add(k);
            }
            if(availableKits.isEmpty()){
                SWHelper.randomKitMode = 1;
                for(Player p :playerList) p.sendMessage("§cRandom kits disabled since there isn't any enabled kit!");
            }
        }

        GameKit randomKit = null;
        if (SWHelper.randomKitMode == 3){
            randomKit = availableKits.get(rand.nextInt(availableKits.size()));
        }

        for(Player p :playerList){
            if(SWHelper.randomKitMode == 2) giveRandomKit(p,gameMap,availableKits);
            else if (SWHelper.randomKitMode == 3) {
                gameMap.setKitVote(p,randomKit);
                p.sendMessage("You got random kit '§e"+randomKit.getName()+"§r'!");
            }else if (SWHelper.randomKitMode == 0){
                gameMap.setKitVote(p, null);
            }

            GameKit k = gameMap.getSelectedKit(p);
            if(k==null) continue;
            if(k.getName().equals("enchanter")){
                p.setLevel(100);
            }
        }

        if(SWHelper.allEventsEnable){
            for(MatchEvent event : e.getGameMap().getEvents()) {
                event.setEnabled(true);
                event.reset();
            }
        }

    }

    private void giveRandomKit(Player player,GameMap map, ArrayList<GameKit> kits){
        GameKit kit = kits.get(rand.nextInt(kits.size()));
        map.setKitVote(player,kit);
        player.sendMessage("You got random kit '§e"+kit.getName()+"§r'!");
    }
}
