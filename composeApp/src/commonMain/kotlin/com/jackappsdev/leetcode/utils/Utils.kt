package com.jackappsdev.leetcode.utils

import kotlin.math.round

fun Int.toCommaString(): String {
    return this.toLong().toCommaString()
}

fun Int.formatWithK(): String {
    return when {
        this >= 1_000_000 -> "${(round(this / 100_000.0) / 10)}M"
        this >= 1_000 -> "${(round(this / 100.0) / 10)}K"
        else -> this.toString()
    }
}

fun Long.toCommaString(): String {
    return this.toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
}
