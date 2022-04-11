package com.github.jmlb23.marvel.data.api

import com.github.jmlb23.marvel.data.api.characters.CharactersResponse
import com.github.jmlb23.marvel.data.api.comics.ComicsResponse

internal interface MarvelApi {
    suspend fun characters(
        limit: Int,
        offset: Int
    ): Result<CharactersResponse>

    suspend fun character(characterId: Long): Result<CharactersResponse>

    suspend fun comics(characterId: Long): Result<ComicsResponse>
}