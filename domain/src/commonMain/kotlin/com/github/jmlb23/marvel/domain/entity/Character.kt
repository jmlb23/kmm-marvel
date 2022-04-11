package com.github.jmlb23.marvel.domain.entity

data class Comic(val name: String, val thumbnail: String)

data class Character(
	val id: Long,
	val name: String,
	val description: String,
	val image: String,
	val comics: List<Comic>
)
