package com.jackappsdev.leetcode.utils

fun Int.toCommaString(): String {
    return this.toLong().toCommaString()
}

fun Long.toCommaString(): String {
    return this.toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
}
