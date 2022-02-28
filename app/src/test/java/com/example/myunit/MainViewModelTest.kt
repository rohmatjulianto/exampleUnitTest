package com.example.myunit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.myunit.data.Post
import com.example.myunit.data.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: MainRepository

    @Mock
    private lateinit var observerUser: Observer<ArrayList<User>>

    @Mock
    private lateinit var observerPost: Observer<Post>

    private lateinit var  mainViewModel: MainViewModel
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        observerUser = mock()
        observerPost = mock()
        mainViewModel = MainViewModel(repository, dispatcher)
    }

    @After
    fun tearDown() {

    }


    @Test
    fun `given MainViewModel when call method getUser() then method getUser() in MainRepository get called`() {
        runTest {
            val dummyUser = arrayListOf(User("1", "udin", "udin@ii.com"))
            Mockito.`when`(repository.getUser()).thenReturn(dummyUser)

            mainViewModel.user.observeForever(observerUser)
            mainViewModel.getUser()
            verify(observerUser).onChanged(dummyUser)
        }
    }

    @Test
    fun `given MainViewModel and MainRepository call getPost()`() {
        runTest {
            val dummyPost = Post(1, "title", "body")
            Mockito.`when`(repository.getPost("1")).thenReturn(dummyPost)

            mainViewModel.post.observeForever(observerPost)
            mainViewModel.getPost("1")
            verify(observerPost).onChanged(dummyPost)
        }
    }
}