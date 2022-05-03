package com.github.jmlb23.marvel.data

import com.github.jmlb23.marvel.data.api.MarvelApi
import com.github.jmlb23.marvel.data.api.impl.MarvelApiImpl
import com.github.jmlb23.marvel.data.repoImpl.CharacterRepository
import com.github.jmlb23.marvel.domain.entity.Character
import com.github.jmlb23.marvel.domain.repo.Repository
import com.github.jmlb23.marvel.domain.usecase.GetCharacterById
import com.github.jmlb23.marvel.domain.usecase.GetCharactersPaginated
import com.github.jmlb23.marvel.domain.usecase.UseCase
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module


val diData = module {
    single {
        HttpClient(engine()) {
            install(ContentNegotiation) {
                json(get())
            }

            install(Logging) {
                logger = io.ktor.client.plugins.logging.Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }

    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }

    single<MarvelApi> {
        MarvelApiImpl(get())
    }

    single<Repository<Character>> { CharacterRepository(get()) }

    single<UseCase<Int, List<Character>>>(named("GetCharactersPaginated")) {
        GetCharactersPaginated(get())
    }

    single<UseCase<Long, Character?>>(named("GetCharacterById")) {
        GetCharacterById(get())
    }
}
