package net.vizja.quadraticequation

import kotlin.math.round

fun <T1 : Any, T2 : Any, T3: Any, R : Any> let(p1: T1?, p2: T2?, p3: T3?, block: (T1, T2, T3) -> R?): R? {
    return when {
        !(p1 == null || p2 == null || p3 == null) -> block(p1, p2, p3)
        else -> null
    }
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}