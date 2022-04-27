package com.github.jmlb23.mvvm

import com.github.jmlb23.marvel.domain.entity.Character
import com.github.jmlb23.marvel.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

actual class CharactersViewModel(actual val getCharactersPaginated: UseCase<Int, List<Character>>){
    private val viewModelScope = CoroutineScope(Dispatchers.Default)
    private val currentPage = MutableStateFlow(0)
    val elements = MutableStateFlow(listOf<Character>())
    val error = MutableStateFlow<String?>(null)

    private fun getCurrentPage() = currentPage.value

    fun nextPage() {
        viewModelScope.launch {
            getCharactersPaginated.exec(currentPage.value).fold({
                elements.value = elements.value + it
                currentPage.value = getCurrentPage() + 1
            }, {
                error.value = it.message ?: ""
            })
        }
    }
}