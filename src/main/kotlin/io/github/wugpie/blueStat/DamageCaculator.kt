package io.github.wugpie.blueStat

import io.github.wugpie.blueStat.util.getStat
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class DamageCaculator : Listener{

    @EventHandler
    fun onDamage(event : EntityDamageByEntityEvent){

        //테스트 용
        if(event.damager is Player){
            val p = event.damager as Player
            val atk = p.getStat("ATK")
            p.sendMessage(atk.toString())
            event.damage *= (1 + ( atk / 100 ) )
            p.sendMessage(event.damage.toString())
        }

    }
}