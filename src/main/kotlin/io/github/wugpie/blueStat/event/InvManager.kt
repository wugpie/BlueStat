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
            when(event.slot){
                11 -> {
                    player.setStat("ATK",  atk + 1)
                    StatCommand.setInventory(event.inventory, player)
                }
                13 -> {
                    player.setStat("RGW",  rgw + 1)
                    StatCommand.setInventory(event.inventory, player)
                }
                15 -> {
                    player.setStat("DEF",  def + 1)
                    StatCommand.setInventory(event.inventory, player)
                }
                29 -> {
                    player.setStat("STB",  stb + 1)
                    StatCommand.setInventory(event.inventory, player)
                }
                31 -> {
                    player.setStat("CRT",  crt + 1)
                    StatCommand.setInventory(event.inventory, player)
                }
                33 -> {
                    player.setStat("AVI",  avi + 1)
                    StatCommand.setInventory(event.inventory, player)
                }
            }

        }
    }

}