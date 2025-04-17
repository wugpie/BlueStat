package io.github.wugpie.blueStat.event

import io.github.wugpie.blueStat.util.getStat
import io.github.wugpie.blueStat.util.setStat
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerExpChangeEvent
import org.bukkit.event.player.PlayerLevelChangeEvent

class LevelManager : Listener {

    val required =  arrayOf(8,10,15,21,28,36,45,55,66,
        79,93,108,124,141,159,178,198,219,241,
        265,288,312,337,363,390,418,447,508,581,
        658,734,814,897,982,1069,1159,1251,1346,1443,
        1543,1645,1750,1857,1966,2078,2192,2309,2428,
        2550,2674,2800,2929,3060,3194,3330,3469,3610,3754,
        3900,4048,4199,4352,4508,4666,4831,5007,5186,5369,
        5556,5747,5942,6141,6344,6552,6768,6989,7216,7449,
        7682,7915,8148,8381,8883,9460,10614,12922,17583,26770)

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
                e.player.sendActionBar("§f[§e알림§f] §c레벨 업§f! (§b${e.oldLevel} §f-> §c${e.newLevel}§f)")
                val p = e.player.getStat("point")
                e.player.setStat("point", p + (5 * (e.newLevel - e.oldLevel)))
            }
            if (e.oldLevel > e.newLevel) {
                e.player.sendActionBar("§f[§e알림§f] §a레벨 다운§f! (§b${e.oldLevel} §f-> §c${e.newLevel}§f)")
            }
        }


    }

    @EventHandler
    fun onExpChange(event : PlayerExpChangeEvent){

        val exp = event.amount
        val lv = event.player.level
        val progress = event.player.exp
        event.amount = 0

        val getExp = (exp.toFloat() / required[lv - 1].toFloat())
        val getLv = (progress + getExp).toInt()
        if(progress + getExp >= 1){
            event.player.level += getLv
        }
        event.player.exp = (progress + getExp)  - getLv
        if(lv >= 90){
            event.player.level = 90
            event.player.exp = 0.0f
            event.player.setStat("exp", 294784)
            event.player.sendActionBar("§f[§e알림§f] §eLV §cMax")
        }else{
            event.player.setStat("exp", event.player.getStat("exp") + exp)
        }


    }

    @EventHandler
    fun onPlayerDeath(event : PlayerDeathEvent){
        event.keepLevel = true
    }

}