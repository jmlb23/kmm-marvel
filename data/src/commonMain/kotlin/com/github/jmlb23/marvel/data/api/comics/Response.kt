package com.github.jmlb23.marvel.data.api.comics

import kotlinx.serialization.Serializable

@Serializable
internal data class ComicsResponse(
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
	val results: List<Comic>
)

@Serializable
internal data class Comic(
	val id: Long?,
	val digitalID: Long?,
	val title: String?,
	val issueNumber: Long?,
	val variantDescription: String?,
	val description: String? = null,
	val modified: String?,
	val isbn: String?,
	val upc: String?,
	val diamondCode: String?,
	val ean: String?,
	val issn: String?,
	val format: String?,
	val pageCount: Long?,
	val textObjects: List<TextObject>,
	val resourceURI: String?,
	val urls: List<URL>,
	val series: Series,
	val variants: List<Series>,
	val dates: List<Date>,
	val prices: List<Price>,
	val thumbnail: Thumbnail,
	val images: List<Thumbnail>,
	val creators: Creators,
	val characters: Characters,
	val stories: Stories,
	val events: Characters
)

@Serializable
internal data class Characters(
	val available: Long?,
	val collectionURI: String?,
	val items: List<Series>,
	val returned: Long?
)

@Serializable
internal data class Series(
	val resourceURI: String?,
	val name: String?
)

@Serializable
internal data class Creators(
	val available: Long?,
	val collectionURI: String?,
	val items: List<CreatorsItem>,
	val returned: Long?
)

@Serializable
internal data class CreatorsItem(
	val resourceURI: String?,
	val name: String?,
	val role: String?
)

@Serializable
data class Date(
	val type: String?,
	val date: String?
)

@Serializable
data class Thumbnail(
	val path: String?,
	val extension: String?
)

@Serializable
data class Price(
	val type: String?,
	val price: Double
)

@Serializable
data class Stories(
	val available: Long?,
	val collectionURI: String?,
	val items: List<StoriesItem>,
	val returned: Long?
)

@Serializable
data class StoriesItem(
	val resourceURI: String?,
	val name: String?,
	val type: String?
)

@Serializable
data class TextObject(
	val type: String?,
	val language: String?,
	val text: String?
)

@Serializable
data class URL(
	val type: String?,
	val url: String?
)
