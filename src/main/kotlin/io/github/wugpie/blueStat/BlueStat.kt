package io.github.wugpie.blueStat

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class BlueStat : JavaPlugin(), Listener {

   private val FileConfigurator = FileConfiguration()

    override fun onEnable() {
        server.consoleSender.sendMessage("{${ChatColor.RED}}ホシノ大好き!")
        server.pluginManager.registerEvents(DamageCaculator(), this)
        server.pluginManager.registerEvents(this, this)

        val folder = File(server.pluginsFolder, "bluestats")
        if(!folder.exists()) folder.mkdir()
    }

    @EventHandler
    fun onFirstJoin(event : PlayerJoinEvent){
        FileConfigurator.createStatFile(event.player)
    }

    override fun onDisable() {
        server.consoleSender.sendMessage("{${ChatColor.GREEN}}ホシノ大好き!")
    }
}
