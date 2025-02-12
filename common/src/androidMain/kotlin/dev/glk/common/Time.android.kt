package dev.glk.common

internal actual fun getCurrentTimestampPlatform(): Long {
    return System.currentTimeMillis()
}