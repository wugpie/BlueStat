package io.github.wugpie.blueStat

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class DamageCaculator : Listener {

    @EventHandler
    fun onDamage(event : EntityDamageByEntityEvent){

        event.entity.sendMessage("{${event.damage}")

    }
}