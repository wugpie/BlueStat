package io.github.wugpie.blueStat.util

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File


fun Player.getStatFile() : File {
    return File("plugins" + File.separator
            + "bluestats" + File.separator
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
    }
}

