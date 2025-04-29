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
            val stb = p.getStat("STB")
            val crt = p.getStat("CRT")
            val rgw = p.getStat("RGW")
            if(event.entity is Player){
                val avi = p.getStat("AVI")
                val def = p.getStat("DEF")
            } else {
                //추가 예정
            }
        }

    }
}