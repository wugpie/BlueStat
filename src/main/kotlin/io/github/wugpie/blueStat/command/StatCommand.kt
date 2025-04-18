package io.github.wugpie.blueStat.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import io.github.wugpie.blueStat.util.getItem
import io.github.wugpie.blueStat.util.getStat
import io.github.wugpie.blueStat.util.getStatFile
import io.papermc.paper.command.brigadier.CommandSourceStack
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

object StatCommand {

    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(
            LiteralArgumentBuilder.literal<CommandSourceStack>("stat")
                .requires { it.sender is Player }
                .executes{ context ->
                    val player = context.source.executor as Player
                    val gui : Inventory = Bukkit.createInventory(null, 54 ,"${player.name}의 스텟창")
                    setInventory(gui,player)
                    player.openInventory(gui)
                    1
                }
        )

    }

    fun setInventory(gui : Inventory, player : Player){
        gui.setItem(11, getItem(Material.IRON_SWORD, 1 , "§c공격력", true
            , "§c근접 공격§f에 §e관여§f하는 §f수치입니다.", "§f현재 수치: §e${player.getStat("ATK")}"))
        gui.setItem(13, getItem(Material.BOW, 1, "§6집중", true
            ,"§6원거리 공격§f에 §e관여§f하는 §f수치입니다.", "§f현재 수치: §e${player.getStat("RGW")}"))
        gui.setItem(15, getItem(Material.DIAMOND_CHESTPLATE, 1, "§b맷집", true
            ,"§b공격력 감소§f에 §e관여§f하는 §f수치입니다.", "§f현재 수치: §e${player.getStat("DEF")}"))
        gui.setItem(29, getItem(Material.EMERALD, 1, "§e안정", true
            ,"§e난수§f에 §e관여§f하는 §f수치입니다.", "§f현재 수치: §e${player.getStat("STB")}"))
        gui.setItem(31, getItem(Material.DIAMOND_AXE, 1, "§4치명", true
            ,"§4치명타§f에 §e관여§f하는 §f수치입니다.", "§f현재 수치: §e${player.getStat("CRT")}"))
        gui.setItem(33, getItem(Material.SHIELD, 1, "§a회피", true
            ,"§a회피§f에 §e관여§f하는 §f수치입니다.", "§f현재 수치: §e${player.getStat("AVI")}"))
        gui.setItem(45, getItem(Material.BOOK, 1, "§f현재 §e스텟 §f포인트 : ${player.getStat("point")}", true, "§f현재 §e경험치 : ${player.getStat("exp")}"))
    }
}