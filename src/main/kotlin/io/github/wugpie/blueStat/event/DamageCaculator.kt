package io.github.wugpie.blueStat.event

import io.github.wugpie.blueStat.util.getStat
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import kotlin.math.atan
import kotlin.random.Random

class DamageCaculator : Listener {

    @EventHandler
    fun onDamageByEntity(event : EntityDamageByEntityEvent){

        if(event.damager is Player) {
            val p = event.damager as Player
            val atk = p.getStat("ATK")
            val stb = p.getStat("STB")
            val crt = p.getStat("CRT")


            //공격력 계수 + 안정치 추가 예정
            event.damage *= (((Math.PI / 1.02576) *
                    atan((atk.toDouble()) / 122)) + 1)

            //크리 추가 예정
            val rate = Random.nextDouble(0.0, 1.0)
            if(rate <= ((0.2 * Math.PI / 1.02576) * atan(crt.toDouble())/122)){
                p.sendMessage("§cCritical")
                event.damage *= 2
            }

            if (event.entity is Player) {
                val e = event.entity as Player
                val avi = e.getStat("AVI")
                val def = e.getStat("DEF")

                //방어력 계수
                event.damage *= (((-1 * (Math.PI / 8.2065)) *
                        atan((def.toDouble()) / 122)) + 1)

                //회피
                val rate2 = Random.nextDouble(0.0, 1.0)
                if(rate2 <= ((0.2 * Math.PI / 1.02576) * atan(avi.toDouble())/122)){
                    e.sendMessage("§e회피")
                    event.isCancelled = true
                }
            }
        }
        else if(event.damager is Snowball || event.damager is EnderPearl ||
            event.damager is Egg || event.damager is Arrow) {

            if((event.damager as Projectile).shooter is Player){
                val p = (event.damager as Projectile).shooter as Player
                val rgw = p.getStat("RGW")

                //집중 계수 + 안정치 추가 예정
                event.damage *= (((Math.PI / 1.02576) *
                        atan((rgw.toDouble()) / 122)) + 1)
            }
        }
    }

    @EventHandler
    fun onDamage(e : EntityDamageEvent){
        //나중에 추가 예정
        if(e.cause == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION ||
            e.cause == EntityDamageEvent.DamageCause.ENTITY_ATTACK ||
            e.cause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION ||
            e.cause == EntityDamageEvent.DamageCause.PROJECTILE) return
        if(e.entity is Player){
            val p = e.entity as Player
            val def = p.getStat("DEF")

            //방어력 계수
            e.damage *= (((-1 * (Math.PI / 8.2065)) *
                    atan((def.toDouble()) / 122)) + 1)
        }
    }
}