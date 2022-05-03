package com.github.jmlb23.mvvm

import com.github.jmlb23.marvel.domain.entity.Character
import com.github.jmlb23.marvel.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

expect class CharactersViewModel(getCharactersPaginated: UseCase<Int, List<Character>>) {

    val elements: MutableStateFlow<List<Character>>
    val error: MutableStateFlow<String?>

    fun getElement(subcription: (List<Character>) -> (Unit))

    fun nextPage()

}