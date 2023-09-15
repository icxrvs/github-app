import com.example.core.domain.User
import com.example.core.usecases.GetAllUsersUseCase
import com.example.core.util.Status
import com.example.githubapp.presentation.list_user.UsersViewModel
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
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UsersViewModelTest {

    private val getAllUsersUseCase: GetAllUsersUseCase = mockk(relaxed = true)
    private lateinit var viewModel: UsersViewModel
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UsersViewModel(getAllUsersUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getAllUsers should post users value`() = runTest {
        val expectedUsers = listOf(User("user1"), User("user2"))
        coEvery { getAllUsersUseCase(any()) } returns flowOf(Status.Success(expectedUsers))

        viewModel.getAllUsers()

        val result = viewModel.users.value

        assertEquals(Status.Success(expectedUsers), result)
    }

    @Test
    fun `test getAllUsers should post error value`() = runTest {
        coEvery { getAllUsersUseCase(any()) } returns flowOf(Status.Error(Throwable("Error message")))

        viewModel.getAllUsers()

        when (val result = viewModel.users.value) {
            is Status.Error -> assertEquals("Error message", result.throwable.message)
            else -> fail("Expected Status.Error but got $result")
        }
    }

    @Test
    fun `test getAllUsers should post empty list`() = runTest {
        coEvery { getAllUsersUseCase(any()) } returns flowOf(Status.Success(emptyList()))

        viewModel.getAllUsers()

        val result = viewModel.users.value

        assertEquals(Status.Success(emptyList<User>()), result)
    }
}
