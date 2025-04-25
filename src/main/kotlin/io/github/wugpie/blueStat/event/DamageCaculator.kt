package io.github.wugpie.blueStat.event

import io.github.wugpie.blueStat.util.getStat
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class DamageCaculator : Listener {

    @EventHandler
    fun onDamage(event : EntityDamageByEntityEvent){

        //나중에 추가!
        if(event.damager is Player){
            val p = event.damager as Player
            val atk = p.getStat("ATK")
        }

    }
}