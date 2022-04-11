package com.github.jmlb23.marvel.data.mappers

import com.github.jmlb23.marvel.data.api.characters.Character
import com.github.jmlb23.marvel.domain.entity.Character as DCharacter
import com.github.jmlb23.marvel.data.api.comics.Comic
import com.github.jmlb23.marvel.domain.entity.Comic as DComic

internal fun Character.toDomain(comic: List<Comic>): DCharacter =
	DCharacter(
		this.id ?: 0,
		this.name ?: "",
		this.description ?: "",
		this.thumbnail?.path + "." + this.thumbnail?.extension,
		comic.map { DComic(it.title.orEmpty(), it.thumbnail.path + "." + it.thumbnail.extension) }
	)