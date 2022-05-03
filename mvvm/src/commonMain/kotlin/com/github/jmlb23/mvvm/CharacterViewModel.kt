package com.github.jmlb23.mvvm

import com.github.jmlb23.marvel.domain.entity.Character
import com.github.jmlb23.marvel.domain.usecase.UseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.definition.Callbacks

expect class CharacterViewModel {
    val getDetailUseCase: UseCase<Long, Character?>

    fun getDetail(id: Long)

    fun detail(callback: (Character) -> Unit, errorCallback: (String) -> Unit)
}