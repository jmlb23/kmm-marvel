package com.github.jmlb23.marvel.domain.repo

interface Repository<out E> {
	suspend fun getElements(page: Int): Result<List<E>>
	suspend fun getElement(id: Long): Result<E?>
}