package com.github.jmlb23.marvel.data

import com.github.jmlb23.marvel.domain.entity.Character
import com.github.jmlb23.marvel.domain.usecase.UseCase
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.*

class DataTest : KoinTest {

    private val characterUseCase: UseCase<Long, Character?> by inject(named("GetCharacterById"))
	private val charactersPaginatedUseCase: UseCase<Int, List<Character>> by inject(named("GetCharactersPaginated"))

	@BeforeTest
	fun setUp() {
		startKoin {
			modules(diData)
		}
	}

	@AfterTest
	fun teardown() {
		stopKoin()
	}

    @Test
    fun getCharacterByIdReturnsValidResult() {
        runBlocking {
            assertNotNull(
				characterUseCase.exec(1011334).getOrNull()
            )
        }
    }

	@Test
	fun getCharactersPaginatedReturnsValidResult() {
		runBlocking {
			assertTrue(
				charactersPaginatedUseCase.exec(1).getOrNull().orEmpty().isNotEmpty()
			)
		}
	}
}