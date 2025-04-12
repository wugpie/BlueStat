package io.github.wugpie.blueStat.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import io.papermc.paper.command.brigadier.CommandSourceStack
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

object StatCommand {

    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(
            LiteralArgumentBuilder.literal<CommandSourceStack>("stat")
                .requires { it.sender is Player }
                .executes{ context ->
                    val player = context.source.executor as Player
                    val gui : Inventory = Bukkit.createInventory(null, 9 ,"호시노 좋아")
                    player.openInventory(gui)
                    1
                }
        )

    }
}