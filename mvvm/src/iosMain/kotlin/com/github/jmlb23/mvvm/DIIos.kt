package com.github.jmlb23.mvvm

import com.github.jmlb23.marvel.data.diData
import com.github.jmlb23.marvel.domain.entity.Character
import com.github.jmlb23.marvel.domain.usecase.UseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private val diIOS = module {
    single {
        CharactersViewModel(get(named("GetCharactersPaginated")))
    }
    single {
        CharacterViewModel(get(named("GetCharacterById")))
    }
}

class IosComponent : KoinComponent {
    val provideCharacterView: CharacterViewModel by inject()
    val provideCharacterListView: CharactersViewModel by inject()
}


private fun koin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
}

fun initKoin() = koin {
    modules(listOf(diData, diIOS))
}.let { Unit }