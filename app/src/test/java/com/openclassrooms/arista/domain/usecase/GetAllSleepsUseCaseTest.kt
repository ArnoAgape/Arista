package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.time.LocalDateTime
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class GetAllSleepsUseCaseTest {

    private lateinit var repository: SleepRepository
    private lateinit var useCase: GetAllSleepsUseCase

    @Before
    fun setUp() {
        repository = mock(SleepRepository::class.java)
        useCase = GetAllSleepsUseCase(repository)
    }

    @Test
    fun `when repository returns sleeps, use case should return them`() = runBlocking {
        val expected = listOf(
            Sleep(
                startTime = LocalDateTime.now(),
                duration = 480,
                quality = 4
            ),
            Sleep(
                startTime = LocalDateTime.now().plusHours(1),
                duration = 810,
                quality = 10
            )
        )
        `when`(repository.getAllSleeps()).thenReturn(Result.success(expected))

        val result = useCase.execute()

        assertTrue(result.isSuccess)
        assertEquals(expected, result.getOrNull())
    }

    @Test
    fun `when repository throws NoSuchElementException, use case should throw it`() = runBlocking {
        `when`(repository.getAllSleeps()).thenThrow(NoSuchElementException("No list of sleeps found"))

        val exception = assertFailsWith<NoSuchElementException> {
            useCase.execute()
        }

        assertEquals("No list of sleeps found", exception.message)
    }
}
