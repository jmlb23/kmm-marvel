package com.github.jmlb23.mvvm

import com.github.jmlb23.marvel.data.diData
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val diIOS = module {
    single {
        CharactersViewModel(get(named("GetCharactersPaginated")))
    }
    single {
        CharacterViewModel(get(named("GetCharacterById")))
    }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(diData,diIOS)
}

class IosComponent : KoinComponent {
    fun provideCharacterView(): CharacterViewModel = get()
    fun provideCharacterListView(): CharactersViewModel = get()
}

fun initKoin() = initKoin{}
