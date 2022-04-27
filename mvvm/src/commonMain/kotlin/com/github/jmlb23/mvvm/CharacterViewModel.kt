package com.github.jmlb23.mvvm

import com.github.jmlb23.marvel.domain.entity.Character
import com.github.jmlb23.marvel.domain.usecase.UseCase

expect class CharacterViewModel {
    val getDetailUseCase: UseCase<Long, Character?>
}