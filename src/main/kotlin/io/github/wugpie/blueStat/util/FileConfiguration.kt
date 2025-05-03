package io.github.wugpie.blueStat.util

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.FileNotFoundException


fun Player.getStatFile() : File {
    return File("plugins" + File.separator
            + "BlueStat" + File.separator
            + "${this.uniqueId}.yml")
}

fun Player.setStat(name : String, value : Int) {
    val stat = YamlConfiguration.loadConfiguration(this.getStatFile())
    stat.set(name, value)
    stat.save(this.getStatFile())
}

fun Player.getStat(name : String) : Int {
    return YamlConfiguration.loadConfiguration(this.getStatFile()).getInt(name)
}

fun Player.resetStatFile() {
    this.apply {
        setStat("ATK", 0) // 공격력
        setStat("DEF", 0) // 방어력
        setStat("STB", 0) // 안정치
        setStat("CRT", 0) // 크리
        setStat("AVI", 0) // 회피
        setStat("RGW", 0) // 집중(활 공격력)
        setStat("point", 0) //스텟 포인트
        setStat("exp", 0) //custom exp
    }
}

fun copyResource(plugin: JavaPlugin, resourceName: String, outputFile: File) {
    if (outputFile.exists()) return

    plugin.getResource(resourceName)?.use { inputStream ->
        outputFile.outputStream().use { outputStream ->
            inputStream.copyTo(outputStream)
        }
    } ?: throw FileNotFoundException("Resource $resourceName not found in plugin JAR.") as Throwable
}