package io.github.wugpie.blueStat

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class DamageCaculator : Listener{

    @EventHandler
    fun onDamage(event : EntityDamageByEntityEvent){

        //테스트 용
        if(event.entity is Player){
            val p = event.entity as Player
            val atk = p.getStat("ATK")
            event.damage *= (1 + ( atk / 100 ) )
        }

    }
}