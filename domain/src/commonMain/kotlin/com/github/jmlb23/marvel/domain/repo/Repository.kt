package com.github.jmlb23.marvel.domain.repo

interface Repository<E> {
	suspend fun getElements(page: Int): Result<List<E>>
	suspend fun getElement(id: Long): Result<E?>
}