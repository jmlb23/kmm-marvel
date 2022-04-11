package com.github.jmlb23.marvel.domain.usecase

import com.github.jmlb23.marvel.domain.entity.Character
import com.github.jmlb23.marvel.domain.repo.Repository

class GetCharacterById(private val repo: Repository<Character>) : UseCase<Long, Character?> {
	override suspend fun exec(input: Long): Result<Character?> =
		repo.getElement(input)
}