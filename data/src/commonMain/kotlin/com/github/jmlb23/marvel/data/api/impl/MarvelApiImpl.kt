package com.github.jmlb23.marvel.data.api.impl

import com.github.jmlb23.marvel.BuildKonfig
import com.github.jmlb23.marvel.data.api.MarvelApi
import com.github.jmlb23.marvel.data.api.characters.CharactersResponse
import com.github.jmlb23.marvel.data.api.comics.ComicsResponse
import com.github.jmlb23.marvel.data.getTsAndHash
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

internal class MarvelApiImpl(private val httpClient: HttpClient) : MarvelApi {
    override suspend fun characters(limit: Int, offset: Int): Result<CharactersResponse> =
        runCatching {
            val (ts, hash) = getTsAndHash()
            httpClient.get("${BuildKonfig.URL_BASE}/characters?limit=$limit&offset=$offset&ts=$ts&apikey=${BuildKonfig.API_KEY}&hash=$hash")
                .body()
        }

    override suspend fun character(characterId: Long): Result<CharactersResponse> = runCatching {
        val (ts, hash) = getTsAndHash()
        httpClient.get("${BuildKonfig.URL_BASE}/characters/$characterId?ts=$ts&apikey=${BuildKonfig.API_KEY}&hash=$hash")
            .body()
    }

    override suspend fun comics(characterId: Long): Result<ComicsResponse> = runCatching {
        val (ts, hash) = getTsAndHash()
        httpClient.get("${BuildKonfig.URL_BASE}/characters/$characterId?ts=$ts&apikey=${BuildKonfig.API_KEY}&hash=$hash")
            .body()
    }
}