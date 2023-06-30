package com.hb.swhelper;

import com.walrusone.skywarsreloaded.game.GameMap;
import com.walrusone.skywarsreloaded.managers.MatchManager;
import com.walrusone.skywarsreloaded.menus.gameoptions.objects.GameKit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SWIntervals implements Runnable{

    @Override
    public void run() {
        KokomiHandler();
    }
    private MatchManager matchManager = MatchManager.get();
    private void KokomiHandler(){

        for(Player p: Bukkit.getOnlinePlayers()){
            GameMap m = matchManager.getPlayerMap(p);
            if(m==null) return;
            GameKit k = m.getSelectedKit(p);
            if(k==null) return;
            if(k.getName().equals("kokomi") && p.isInWater()) {
                PotionEffect regeneration = new PotionEffect(PotionEffectType.REGENERATION, 60, 0, true, true);
                p.addPotionEffect(regeneration);
            }
        }
    }
}
