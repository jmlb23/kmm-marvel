package com.github.jmlb23.marvel

import androidx.lifecycle.viewModelScope
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.jmlb23.marvel.character.CharacterViewModel
import com.github.jmlb23.marvel.characters.CharactersViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.job
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.koin.core.component.inject
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class ViewModelsTest : KoinTest {
	private val characterViewModel by inject<CharacterViewModel>()
	private val charactersViewModel by inject<CharactersViewModel>()

	@Test
	fun testGetCharacters() {
		runBlocking {
			charactersViewModel.nextPage()
			charactersViewModel.viewModelScope.coroutineContext.job.children.forEach { it.join() }
			charactersViewModel.elements.firstOrNull().let{
				assertTrue(it.orEmpty().isNotEmpty())
			}
		}
	}

	@Test
	fun testGetCharacter() {
		runBlocking {
			characterViewModel.getDetail(1011334)
			characterViewModel.viewModelScope.coroutineContext.job.children.forEach { it.join() }
			characterViewModel.detail.firstOrNull().let{
				assertNotNull(it)
			}
		}
	}
}