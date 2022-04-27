package com.github.jmlb23.mvvm

import com.github.jmlb23.marvel.domain.entity.Character
import com.github.jmlb23.marvel.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

actual class CharacterViewModel(actual val getDetailUseCase: UseCase<Long, Character?>) {
    private val viewModelScope = CoroutineScope(Dispatchers.Default)
    private val _detail = MutableStateFlow<Character?>(null)
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.filterNotNull()
    val detail = _detail.filterNotNull()

    fun getDetail(id: Long) {
        viewModelScope.launch(Dispatchers.Default) {
            getDetailUseCase.exec(id).fold({
                _detail.value = it
            }, {
                _errorMessage.value = it.message
            })
        }
    }
}