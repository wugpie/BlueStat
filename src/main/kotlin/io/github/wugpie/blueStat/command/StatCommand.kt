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
import kotlin.math.atan

object StatCommand {

    fun build() : LiteralArgumentBuilder<CommandSourceStack>{
        return LiteralArgumentBuilder.literal<CommandSourceStack>("stat")
            .requires { it.sender is Player }
            .executes{ context ->
                val player = context.source.executor as Player
                val gui : Inventory = Bukkit.createInventory(null, 54 ,"${player.name}의 스텟창")
                setInventory(gui,player)
                player.openInventory(gui)
                1
            }
    }
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
            , "§c근접 공격§f에 §e관여§f하는 §f수치입니다."))
        gui.setItem(20, getItem(Material.SNOWBALL, 1, "§f현재 수치: §e${player.getStat("ATK")}", true, "§f현재 §c공격력 §f계수: §e${String.format("%.2f",((((Math.PI / 1.02576) * atan((player.getStat("ATK").toDouble())/122)).toFloat()) + 1) * 100)}%"))
        gui.setItem(13, getItem(Material.BOW, 1, "§6집중", true
            ,"§6원거리 공격§f에 §e관여§f하는 §f수치입니다."))
        gui.setItem(22, getItem(Material.SNOWBALL, 1, "§f현재 수치: §e${player.getStat("RGW")}", true, "§f현재 §6집중 §f계수: §e${String.format("%.2f",((((Math.PI / 1.02576) * atan((player.getStat("RGW").toDouble())/122)).toFloat()) + 1) * 100)}%"))
        gui.setItem(15, getItem(Material.DIAMOND_CHESTPLATE, 1, "§b맷집", true
            ,"§b공격력 감소§f에 §e관여§f하는 §f수치입니다."))
        gui.setItem(24, getItem(Material.SNOWBALL, 1, "§f현재 수치: §e${player.getStat("DEF")}", true, "§f현재 §b받는 §f데미지 계수: §e${String.format("%.2f",((((-1 * (Math.PI / 8.2065)) * atan((player.getStat("DEF").toDouble())/122)).toFloat()) + 1) * 100)}%"))
        gui.setItem(29, getItem(Material.EMERALD, 1, "§e안정", true
            ,"§e난수§f에 §e관여§f하는 §f수치입니다."))
        gui.setItem(38, getItem(Material.SNOWBALL, 1, "§f현재 수치: §e${player.getStat("STB")}", true, "§e확률 §f미공개"))
        gui.setItem(31, getItem(Material.DIAMOND_AXE, 1, "§4치명", true
            ,"§4치명타§f에 §e관여§f하는 §f수치입니다."))
        gui.setItem(40, getItem(Material.SNOWBALL, 1, "§f현재 수치: §e${player.getStat("CRT")}", true, "§f현재 §4크리티컬 §f확률: §e${String.format("%.2f",((0.2 * Math.PI / 1.02576) * atan((player.getStat("CRT").toDouble())/122)).toFloat() * 100)}%"))
        gui.setItem(33, getItem(Material.SHIELD, 1, "§a회피", true
            ,"§a회피§f에 §e관여§f하는 §f수치입니다."))
        gui.setItem(42, getItem(Material.SNOWBALL, 1, "§f현재 수치: §e${player.getStat("AVI")}", true, "§f현재 §a회피 §f확률: §e${String.format("%.2f",((0.2 * Math.PI / 1.02576) * atan((player.getStat("AVI").toDouble())/122)).toFloat() * 100)}%"))
        gui.setItem(45, getItem(Material.BOOK, 1, "§f현재 §e스텟 §f포인트 : ${player.getStat("point")}", true, "§f현재 §e경험치 : ${player.getStat("exp")}"))
    }
}