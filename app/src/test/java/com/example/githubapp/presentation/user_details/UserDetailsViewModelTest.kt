package com.example.githubapp.presentation.user_details

import com.example.core.domain.User
import com.example.core.domain.UserRepositories
import com.example.core.usecases.GetAllUserRepositoriesUseCase
import com.example.core.usecases.GetUserDetailUseCase
import com.example.core.util.Status
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UserDetailsViewModelTest{

    private val getUserDetailsUseCase: GetUserDetailUseCase = mockk(relaxed = true)
    private val getAllUserRepositoriesUseCase: GetAllUserRepositoriesUseCase = mockk(relaxed = true)
    private lateinit var viewModel: UserDetailsViewModel
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UserDetailsViewModel(getUserDetailsUseCase,getAllUserRepositoriesUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when getUserDetails are called should post user value`() = runTest {
        val expectedUsers = User(login = "icarus", bio = "Android | iOS Developer")

        coEvery { getUserDetailsUseCase(any()) } returns flowOf(Status.Success(expectedUsers))

        viewModel.getUserDetails("icarusdevv")

        val result = viewModel.user.value

        assertEquals(Status.Success(expectedUsers), result)
    }


    @Test
    fun `when getUserDetails are called should post error value`() = runTest {
        coEvery { getUserDetailsUseCase(any()) } returns flowOf(Status.Error(Throwable("Error message")))

        viewModel.getUserDetails("icarusdevv")

        when (val result = viewModel.user.value) {
            is Status.Error -> assertEquals("Error message", result.throwable.message)
            else -> fail("Expected Status.Error but got $result")
        }
    }

    @Test
    fun `when getAllUserRepositoriesUseCase are called should return a list of user repositories`() = runTest {
        val expectedList = listOf(UserRepositories())
        coEvery { getAllUserRepositoriesUseCase(any()) } returns flowOf(Status.Success(expectedList))

        viewModel.getAllUserRepositories("icarusdevv")

        val result = viewModel.userRepositories.value

        assertEquals(Status.Success(expectedList), result)
    }


    @Test
    fun `when getAllUsers are called should post error value`() = runTest {
        coEvery { getAllUserRepositoriesUseCase(any()) } returns flowOf(Status.Error(Throwable("Error message")))

        viewModel.getAllUserRepositories("icarusdevv")

        when (val result = viewModel.userRepositories.value) {
            is Status.Error -> assertEquals("Error message", result.throwable.message)
            else -> fail("Expected Status.Error but got $result")
        }
    }

    @Test
    fun `when getAllUsers are called should post empty list`() = runTest {
        coEvery { getAllUserRepositoriesUseCase(any()) } returns flowOf(Status.Success(emptyList()))

        viewModel.getAllUserRepositories("icarusdevv")

        val result = viewModel.userRepositories.value

        assertEquals(Status.Success(emptyList<User>()), result)
    }

}