package com.github.jmlb23.marvel.data

import com.github.jmlb23.marvel.BuildKonfig
import io.ktor.util.date.*
import io.ktor.utils.io.core.*
import okio.ByteString

fun getTsAndHash(): Pair<String, String> {
    val ts = GMTDate().timestamp.toString()
    val md5 =
        ByteString.of(*(ts + BuildKonfig.API_KEY_PRIVATE + BuildKonfig.API_KEY).toByteArray())
            .md5().hex()
    return Pair(ts, md5)
}