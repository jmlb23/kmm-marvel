package com.github.jmlb23.marvel.data.api.characters

import kotlinx.serialization.Serializable

@Serializable
internal data class CharactersResponse(
	val code: Long?,
	val status: String?,
	val copyright: String?,
	val attributionText: String?,
	val attributionHTML: String?,
	val etag: String?,
	val data: Data?
)

@Serializable
internal data class Data(
	val offset: Long?,
	val limit: Long?,
	val total: Long?,
	val count: Long?,
	val results: List<Character>?
)

@Serializable
internal data class Character(
	val id: Long?,
	val name: String?,
	val description: String?,
	val modified: String?,
	val thumbnail: Thumbnail?,
	val resourceURI: String?,
	val comics: Comics?,
	val series: Comics?,
	val stories: Stories?,
	val events: Comics?,
	val urls: List<URL>
)

@Serializable
internal data class Comics(
	val available: Long?,
	val collectionURI: String?,
	val items: List<ComicsItem>?,
	val returned: Long?
)

@Serializable
internal data class ComicsItem(
	val resourceURI: String?,
	val name: String?
)

@Serializable
data class Stories(
	val available: Long?,
	val collectionURI: String?,
	val items: List<StoriesItem>?,
	val returned: Long?
)

@Serializable

data class StoriesItem(
	val resourceURI: String?,
	val name: String?,
	val type: String?
)

@Serializable
data class Thumbnail(
	val path: String?,
	val extension: String?
)

@Serializable
data class URL(
	val type: String?,
	val url: String?
)