package com.github.jmlb23.mvvm

import com.github.jmlb23.marvel.domain.entity.Character
import com.github.jmlb23.marvel.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

actual class CharacterViewModel(actual val getDetailUseCase: UseCase<Long, Character?>) {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)
    private val _detail = MutableStateFlow<Character?>(null)
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.filterNotNull()
    val detail = _detail.filterNotNull()
    var job: Job? = null

    actual fun getDetail(id: Long) {
        viewModelScope.launch(Dispatchers.Main) {
            getDetailUseCase.exec(id).fold({
                _detail.value = it
            }, {
                _errorMessage.value = it.message
            })
        }
    }

    actual fun detail(callback: (Character) -> Unit, errorCallback: (String) -> Unit) {
        job = viewModelScope.launch(Dispatchers.Main) {
            detail.onEach { callback(it) }.catch { errorCallback(it.message ?: "") }.collect()
        }
    }

    fun onCleared(){
        job?.cancel()
    }
}