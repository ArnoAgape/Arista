package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class GetAllUserUseCaseTest {

    private lateinit var repository: UserRepository
    private lateinit var useCase: GetUserUsecase

    @Before
    fun setUp() {
        repository = mock(UserRepository::class.java)
        useCase = GetUserUsecase(repository)
    }


    @Test
    fun `when repository returns a user, use case should return it`() = runBlocking {
        // Arrange
        val expected =
            User(
                id = 0,
                nickname = "Josef",
                email = "josef@gmail.com",
                gender = "female",
                age = 25,
                weight = 70.0,
                height = 179.2
            )
        `when`(repository.getUser()).thenReturn(expected)
        // Act
        val result = useCase.execute()


        // Assert
        assertEquals(expected, result)
    }


    @Test
    fun `when repository throws NoSuchElementException, use case should throw it`() = runBlocking {
        `when`(repository.getUser()).thenThrow(NoSuchElementException("No user found"))

        val exception = assertFailsWith<NoSuchElementException> {
            useCase.execute()
        }

        assertEquals("No user found", exception.message)
    }


}