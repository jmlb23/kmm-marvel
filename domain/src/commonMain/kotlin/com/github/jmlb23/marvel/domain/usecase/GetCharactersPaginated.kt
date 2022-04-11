package com.github.jmlb23.marvel.domain.usecase

import com.github.jmlb23.marvel.domain.entity.Character
import com.github.jmlb23.marvel.domain.repo.Repository

class GetCharactersPaginated(private val repo: Repository<Character>) :
	UseCase<Int, List<Character>> {
	override suspend fun exec(input: Int): Result<List<Character>> =
		repo.getElements(input)

}