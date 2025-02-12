package dev.glk.common

import kotlinx.coroutines.delay

@Throws(Exception::class)
suspend fun getResponse(userId: Int, imageId: String): String {
    delay(1000)
    return getResponseInternal(userId, imageId)
}

internal fun getResponseInternal(userId: Int, imageId: String): String {
    return "Response for user $userId and image $imageId"
}