package com.github.jmlb23.marvel.data

import com.github.jmlb23.marvel.BuildKonfig
import java.security.MessageDigest
import java.util.*

actual fun getTsAndHash(): Pair<String, String> {
    val messageDigest = MessageDigest.getInstance("MD5")
    val ts = Date().time.toString()
    val md5 = messageDigest.digest(
        (ts + BuildKonfig.API_KEY_PRIVATE + BuildKonfig.API_KEY).toByteArray()
    ).joinToString("") { "%02x".format(it) }
    return Pair(ts, md5)
}