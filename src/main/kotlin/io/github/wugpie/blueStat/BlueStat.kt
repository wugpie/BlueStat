package io.github.wugpie.blueStat

import io.github.wugpie.blueStat.command.StatCommand
import io.github.wugpie.blueStat.command.StatSetCommand
import io.github.wugpie.blueStat.event.DamageCaculator
import io.github.wugpie.blueStat.event.InvManager
import io.github.wugpie.blueStat.event.LevelManager
import io.github.wugpie.blueStat.util.copyResource
import io.github.wugpie.blueStat.util.getStatFile
import io.github.wugpie.blueStat.util.resetStatFile
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.io.File


class BlueStat : JavaPlugin(), Listener {


    override fun onEnable() {

        server.consoleSender.sendMessage("${ChatColor.RED}ホシノ大好き!")

        //이벤트 등록
        server.pluginManager.registerEvents(DamageCaculator(), this)
        server.pluginManager.registerEvents(InvManager(), this)
        server.pluginManager.registerEvents(LevelManager(), this)
        server.pluginManager.registerEvents(this, this)

        //초기 폴더 생성
        val folder = File(server.pluginsFolder, "BlueStat")
        if(!folder.exists()) folder.mkdir()
        //config 설정
        val config = File("plugins" + File.separator
                + "BlueStat" + File.separator
                + "config.yml")
        copyResource(this, "config.yml", config)

        //brigadier 명령어 등록
        val manager = this.lifecycleManager
        manager.registerEventHandler(LifecycleEvents.COMMANDS) { event ->
            val dispatcher = event.registrar().dispatcher
            if (event.registrar().dispatcher != null) {
                dispatcher.register(StatCommand.build())
                dispatcher.register(StatSetCommand.build())
                logger.info("Brigadier command registered successfully.")
            } else {
                logger.severe("Failed to register Brigadier command.")
            }
        }
    }

    //초기 파일 생성
    @EventHandler
    fun onFirstJoin(event : PlayerJoinEvent) {
        if(event.player.level == 0) event.player.level = 1
        if(!event.player.getStatFile().exists()) event.player.resetStatFile()
    }


    override fun onDisable() {
        server.consoleSender.sendMessage("${ChatColor.GREEN}ホシノ大好き!")
    }
}
