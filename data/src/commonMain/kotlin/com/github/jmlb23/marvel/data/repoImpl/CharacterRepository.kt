package com.github.jmlb23.marvel.data.repoImpl

import com.github.jmlb23.marvel.data.api.MarvelApi
import com.github.jmlb23.marvel.data.mappers.toDomain
import com.github.jmlb23.marvel.domain.entity.Character
import com.github.jmlb23.marvel.domain.repo.Repository

class CharacterRepository internal constructor(private val api: MarvelApi) : Repository<Character> {
    override suspend fun getElements(page: Int): Result<List<Character>> {
        return api.characters(offset = page * 10, limit = 10)
            .map { it.data?.results.orEmpty().map { it.toDomain(emptyList()) } }

    }

    override suspend fun getElement(id: Long): Result<Character?> {
        val comics = api.comics(id).getOrNull()?.data?.results.orEmpty()
        return api.character(id).map { it.data?.results?.firstOrNull()?.toDomain(comics) }
    }
}