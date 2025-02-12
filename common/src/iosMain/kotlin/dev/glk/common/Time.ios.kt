package dev.glk.common

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

internal actual fun getCurrentTimestampPlatform(): Long {
    return (NSDate().timeIntervalSince1970() * 1000L).toLong()
}