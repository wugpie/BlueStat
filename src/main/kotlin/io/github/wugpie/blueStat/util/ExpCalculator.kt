package io.github.wugpie.blueStat.util

import java.io.File
import kotlin.text.toInt

fun calExp(exp : Int) : Pair<Int, Float>{
    val require = File("plugins" + File.separator
            + "BlueStat" + File.separator
            + "config.yml")
    val required = require.readLines().map { it.toInt() }

    var xp = exp
    var level = 1
    var progress = 0.0f

    while(xp >= 0){
        xp -= required[level-1]
        level += 1
    }
    xp += required[level - 2]
    level -= 1
    progress = (xp.toFloat() / required[level - 1].toFloat())

    return Pair(level, progress)
}