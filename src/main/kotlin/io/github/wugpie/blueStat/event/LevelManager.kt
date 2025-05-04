package io.github.wugpie.blueStat.event

import io.github.wugpie.blueStat.util.calExp
import io.github.wugpie.blueStat.util.getStat
import io.github.wugpie.blueStat.util.setStat
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerExpChangeEvent
import org.bukkit.event.player.PlayerLevelChangeEvent

class LevelManager : Listener {

    @EventHandler
    fun onLevelChange(e : PlayerLevelChangeEvent){
        if(e.newLevel > 90){
            e.player.level = 90
            e.player.exp = 0.0f
            e.player.setStat("exp", 294784)
            e.player.sendActionBar("§f[§e알림§f] §eLV §cMax")
        }
        else{
            if (e.oldLevel < e.newLevel) {
                e.player.sendMessage("§f[§e알림§f] §c레벨 업§f! (§b${e.oldLevel} §f-> §c${e.newLevel}§f)")
                val p = e.player.getStat("point")
                e.player.setStat("point", p + (5 * (e.newLevel - e.oldLevel)))
            }
            if (e.oldLevel > e.newLevel) {
                e.player.sendMessage("§f[§e알림§f] §a레벨 다운§f! (§b${e.oldLevel} §f-> §c${e.newLevel}§f)")
            }
        }
    }

    @EventHandler
    fun onExpChange(event : PlayerExpChangeEvent){

        val exp = event.amount
        event.amount = 0

        if((event.player.getStat("exp") + exp) < 294784){
            event.player.sendActionBar("§f[§e알림§f] §f${exp} §eXP")
            val xp = calExp(event.player.getStat("exp") + exp)
            event.player.level = xp.first
            event.player.exp = xp.second
            event.player.setStat("exp", event.player.getStat("exp") + exp)
        } else {
            event.player.level = 90
            event.player.exp = 0.0f
            event.player.setStat("exp", 294784)
            event.player.sendActionBar("§f[§e알림§f] §eLV §cMax")
        }
    }

    @EventHandler
    fun onPlayerDeath(event : PlayerDeathEvent){
        event.keepLevel = true
    }

}