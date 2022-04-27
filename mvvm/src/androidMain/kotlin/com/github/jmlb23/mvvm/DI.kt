package com.github.jmlb23.mvvm

import com.github.jmlb23.marvel.data.diData
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module

val diAndroid = module {
    loadKoinModules(diData)
    viewModel {
        CharactersViewModel(get(named("GetCharactersPaginated")))
    }
    viewModel {
        CharacterViewModel(get(named("GetCharacterById")))
    }
}
