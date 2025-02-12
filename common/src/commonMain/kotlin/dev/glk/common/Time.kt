package dev.glk.common

fun getCurrentTimestamp(): Long {
    return getCurrentTimestampPlatform()
}

internal expect fun getCurrentTimestampPlatform(): Long