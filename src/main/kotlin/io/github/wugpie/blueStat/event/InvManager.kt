package io.github.wugpie.blueStat.event

import io.github.wugpie.blueStat.command.StatCommand
import io.github.wugpie.blueStat.util.getStat
import io.github.wugpie.blueStat.util.setStat
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class InvManager : Listener {

    @EventHandler
    fun onInventoryClick(event : InventoryClickEvent){
        val player = event.whoClicked as Player
        val atk = player.getStat("ATK")
        val def = player.getStat("DEF")
        val stb = player.getStat("STB")
        val crt = player.getStat("CRT")
        val avi = player.getStat("AVI")
        val rgw = player.getStat("RGW")
        if(event.view.title == "${player.name}의 스텟창"){
            event.isCancelled = true
            val point = player.getStat("point")
            when(event.slot){
                20 -> {
                    if(point > 0){
                        player.setStat("ATK",  atk + 1)
                        player.setStat("point", point - 1)
                    } else {
                        player.sendMessage("§f[§e알림§f] §e포인트§f가 §c부족§f합니다")
                    }

                    StatCommand.setInventory(event.inventory, player)
                }
                22 -> {
                    if(point > 0){
                        player.setStat("RGW",  rgw + 1)
                        player.setStat("point", point - 1)
                    } else {
                        player.sendMessage("§f[§e알림§f] §e포인트§f가 §c부족§f합니다")
                    }
                    StatCommand.setInventory(event.inventory, player)
                }
                24 -> {
                    if(point > 0){
                        player.setStat("DEF",  def + 1)
                        player.setStat("point", point - 1)
                    } else {
                        player.sendMessage("§f[§e알림§f] §e포인트§f가 §c부족§f합니다")
                    }
                    StatCommand.setInventory(event.inventory, player)
                }
                38 -> {
                    if(point > 0){
                        player.setStat("STB",  stb + 1)
                        player.setStat("point", point - 1)
                    } else {
                        player.sendMessage("§f[§e알림§f] §e포인트§f가 §c부족§f합니다")
                    }
                    StatCommand.setInventory(event.inventory, player)
                }
                40 -> {
                    if(point > 0){
                        player.setStat("CRT",  crt + 1)
                        player.setStat("point", point - 1)
                    } else {
                     player.sendMessage("§f[§e알림§f] §e포인트§f가 §c부족§f합니다")
                    }
                    StatCommand.setInventory(event.inventory, player)
                }
                42 -> {
                    if(point > 0){
                        player.setStat("AVI",  avi + 1)
                        player.setStat("point", point - 1)
                    } else {
                        player.sendMessage("§f[§e알림§f] §e포인트§f가 §c부족§f합니다")
                    }
                    StatCommand.setInventory(event.inventory, player)
                }
            }

        }
    }

}