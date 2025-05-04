package io.github.wugpie.blueStat.event

import io.github.wugpie.blueStat.util.getStat
import org.bukkit.entity.Arrow
import org.bukkit.entity.Egg
import org.bukkit.entity.EnderPearl
import org.bukkit.entity.Player
import org.bukkit.entity.Projectile
import org.bukkit.entity.Snowball
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.ProjectileHitEvent
import kotlin.math.atan

class DamageCaculator : Listener {

    @EventHandler
    fun onDamageByEntity(event : EntityDamageByEntityEvent){

        //나중에 추가!
        if(event.damager is Player){
            val p = event.damager as Player
            val atk = p.getStat("ATK")
            val stb = p.getStat("STB")
            val crt = p.getStat("CRT")


            //공격력 계수 + 안정치 추가 예정
            event.damage *= (((Math.PI / 1.02576) *
                    atan((atk.toDouble()) / 122)) + 1)

            //크리 추가 예정
            if(event.entity is Player){
                val e = event.entity as Player
                val avi = e.getStat("AVI")
                val def = e.getStat("DEF")

                //방어력 계수
                event.damage *= (((-1 * (Math.PI / 8.2065)) *
                        atan((def.toDouble()) / 122)) + 1)

                //회피 추가 예정

            }
            else if(event.entity is Snowball || event.entity is EnderPearl ||
                event.entity is Egg || event.entity is Arrow) {


                if((event.entity as Projectile).shooter is Player){
                    val p = (event.entity as Projectile).shooter as Player
                    val rgw = p.getStat("RGW")
                    //집중 계수 + 안정치 추가 예정
                    event.damage *= (((Math.PI / 1.02576) *
                            atan((rgw.toDouble()) / 122)) + 1)
                }
            }
        }
    }

    @EventHandler
    fun onDamage(e : EntityDamageEvent){
        //나중에 추가 예정
    }
}