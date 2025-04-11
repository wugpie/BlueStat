package io.github.wugpie.blueStat

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class BlueStat : JavaPlugin(), Listener {


    override fun onEnable() {
        server.consoleSender.sendMessage("${ChatColor.RED}ホシノ大好き!")
        server.pluginManager.registerEvents(DamageCaculator(), this)
        server.pluginManager.registerEvents(this, this)

        //초기 폴더 생성
        val folder = File(server.pluginsFolder, "bluestats")
        if(!folder.exists()) folder.mkdir()
    }

    //초기 파일 생성
    @EventHandler
    fun onFirstJoin(event : PlayerJoinEvent) {
        event.player.resetStatFile()
    }

    override fun onDisable() {
        server.consoleSender.sendMessage("${ChatColor.GREEN}ホシノ大好き!")
    }
}
