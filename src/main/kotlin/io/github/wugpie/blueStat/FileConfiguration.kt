package io.github.wugpie.blueStat

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File
import java.nio.file.Path
import java.nio.file.attribute.FileAttribute
import kotlin.io.path.*

class FileConfiguration() {

    fun getStatFile(player : Player) : File{
       return File( "plugins" + File.separator+ "bluestats" + File.separator + "${player.uniqueId}.yml")
    }

    fun createStatFile(player : Player){
        var stat = YamlConfiguration.loadConfiguration(getStatFile(player))
        stat.set("hello", 0)
        stat.save(getStatFile(player))
    }

}