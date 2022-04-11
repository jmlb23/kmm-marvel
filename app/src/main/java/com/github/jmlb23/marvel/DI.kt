package com.github.jmlb23.marvel

import com.github.jmlb23.marvel.character.CharacterViewModel
import com.github.jmlb23.marvel.characters.CharactersViewModel
import com.github.jmlb23.marvel.domain.entity.Character
import com.github.jmlb23.marvel.domain.usecase.UseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val diAndroid = module {
    viewModel {
        CharactersViewModel(get(named("GetCharactersPaginated")))
    }
    viewModel {
        CharacterViewModel(get(named("GetCharacterById")))
    }
}
