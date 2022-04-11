package com.github.jmlb23.marvel.domain.usecase

interface UseCase<in I, out O> {
	suspend fun exec(input: I): Result<O>
}