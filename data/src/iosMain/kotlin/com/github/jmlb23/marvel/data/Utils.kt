package com.github.jmlb23.marvel.data
import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

actual fun engine(): HttpClientEngineFactory<HttpClientEngineConfig> = Darwin
