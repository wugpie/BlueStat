package io.github.wugpie.blueStat.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.ImmutableStringReader
import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.exceptions.CommandSyntaxException
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
                                player.sendMessage(statName)
                                player.sendMessage(statValue.toString())
                                1
                            }
                    )
            )
    }
    /** fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(
            LiteralArgumentBuilder.literal<CommandSourceStack>("setstat")
                .then(
                    argument("statName", StatArgumentType())
                        .then(
                            argument("statValue", IntegerArgumentType.integer(0, 450))
                                .requires { it.sender is Player }
                                .executes{ context ->
                                    val player = context.source.executor as Player
                                    val stat = context.getArgument("statValue", StatArgumentType::class.java)
                                    val statName = stat.toString()
                                    val statValue = IntegerArgumentType.getInteger(context, "statValue")
                                    player.sendMessage(statName)
                                    player.sendMessage(statValue.toString())
                                    1
                                }
                        )
                )
        )

    }**/

}