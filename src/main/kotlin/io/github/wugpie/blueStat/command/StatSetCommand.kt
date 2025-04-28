package io.github.wugpie.blueStat.command

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.exceptions.CommandSyntaxException
import io.github.wugpie.blueStat.util.setStat
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands.argument
import org.bukkit.entity.Player

object StatSetCommand {
    fun build() : LiteralArgumentBuilder<CommandSourceStack>{
        return LiteralArgumentBuilder.literal<CommandSourceStack>("setstat")
            .then(
                argument("statName", StringArgumentType.string())
                    .suggests { context, builder ->
                        listOf("strength", "concentration", "defense", "aviation", "critical", "stability")
                            .filter { it.startsWith(builder.remaining) }
                            .forEach { builder.suggest(it) }
                        builder.buildFuture()
                    }
                    .then(
                        argument("statValue", IntegerArgumentType.integer(0, 450))
                            .requires { it.sender is Player }
                            .executes{ context ->
                                val player = context.source.executor as Player
                                val stat = StringArgumentType.getString(context, "statName")
                                val validStats = listOf("strength", "concentration", "defense", "aviation", "critical", "stability")
                                if (stat !in validStats) {
                                    val reader = StringReader(context.input).apply {
                                        cursor = context.range.start
                                    }
                                    throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.literalIncorrect()
                                        .createWithContext(reader, stat)
                                }

                                val statName = stat
                                val statValue = IntegerArgumentType.getInteger(context, "statValue")
                                when(statName){
                                    "strength" -> {
                                        player.setStat("ATK", statValue)
                                        player.sendMessage("§e${player.name}§f님의 §c공격력 §e스탯§f을 §e${statValue}§f로 §e변경했습니다")
                                    }
                                    "concentration" -> {
                                        player.setStat("RGW", statValue)
                                        player.sendMessage("§e${player.name}§f님의 §6집중 §e스탯§f을 §e${statValue}§f로 §e변경했습니다")
                                    }
                                    "defense" -> {
                                        player.setStat("DEF", statValue)
                                        player.sendMessage("§e${player.name}§f님의 §b방어력 §e스탯§f을 §e${statValue}§f로 §e변경했습니다")
                                    }
                                    "aviation" -> {
                                        player.setStat("AVI", statValue)
                                        player.sendMessage("§e${player.name}§f님의 §a회피 §e스탯§f을 §e${statValue}§f로 §e변경했습니다")
                                    }
                                    "stability" -> {
                                        player.setStat("STB", statValue)
                                        player.sendMessage("§e${player.name}§f님의 §e안정 §e스탯§f을 §e${statValue}§f로 §e변경했습니다")
                                    }
                                    "critical" -> {
                                        player.setStat("CRT", statValue)
                                        player.sendMessage("§e${player.name}§f님의 §4치명타 §e스탯§f을 §e${statValue}§f로 §e변경했습니다")
                                    }
                                }
                                1
                            }
                    )
            )
    }
}