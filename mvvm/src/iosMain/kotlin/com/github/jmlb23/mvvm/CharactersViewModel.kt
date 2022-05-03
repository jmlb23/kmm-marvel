package com.github.jmlb23.mvvm

import com.github.jmlb23.marvel.domain.entity.Character
import com.github.jmlb23.marvel.domain.usecase.UseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import platform.darwin.*
import kotlin.coroutines.CoroutineContext

actual class CharactersViewModel actual constructor(private val getCharactersPaginated: UseCase<Int, List<Character>>) {
    private val viewModelScope = CoroutineScope(object : CoroutineDispatcher() {
        override fun dispatch(context: CoroutineContext, block: Runnable) {
            val queue = dispatch_get_main_queue()
            dispatch_async(queue) {
                block.run()
            }
        }
    })

    private val currentPage = MutableStateFlow(0)
    actual val elements = MutableStateFlow(listOf<Character>())
    actual val error = MutableStateFlow<String?>(null)
    var job: Job? = null

    private fun getCurrentPage() = currentPage.value


    actual fun nextPage() {
        viewModelScope.launch {
            getCharactersPaginated.exec(currentPage.value).fold({
                elements.value = elements.value + it
                currentPage.value = getCurrentPage() + 1
            }, {
                error.value = it.message ?: ""
            })
        }
    }

    actual fun getElement(subcription: (List<Character>) -> Unit) {
        job = viewModelScope.launch {
            elements
                .onEach { println("----> ${it.joinToString(",")}") }
                .onEach(subcription)
                .collect()
        }
    }

    fun onCleared(){
        job?.cancel()
    }

}