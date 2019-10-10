package com.example.jsonplaceholderalbums

import com.example.jsonplaceholderalbums.model.Album
import com.example.jsonplaceholderalbums.model.ApiInterface
import com.example.jsonplaceholderalbums.viewmodel.CustomViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.example.jsonplaceholderalbums.model.Network
import io.reactivex.Maybe
import junit.framework.Assert.*
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.junit.Rule
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.mock
import org.junit.runners.model.FrameworkMethod
import org.junit.rules.MethodRule
import org.junit.ClassRule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement


@RunWith(JUnit4::class)
class UnitTest {
    @get:Rule
    val instantTaskExecutorRule = UnitTest.MyRule("@Rule 1");

    @Mock
    lateinit var apiInterface: ApiInterface

    private lateinit var viewModel: CustomViewModel

    @Mock
    lateinit var observer: Observer<ArrayList<Album>>

    @Mock
    lateinit var apiClient: Network

    @Mock
    lateinit var lifecycleOwner: LifecycleOwner
    lateinit var lifecycle: Lifecycle

    class MyRule(private val name: String) : TestRule {
        override fun apply(base: Statement, description: Description?)
                = MyStatement(base)

        inner class MyStatement(private val base: Statement) : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                println("$name Before TestRule")
                try {
                    base.evaluate()
                } finally {
                    println("$name After TestRule")
                }
            }
        }
    }
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = CustomViewModel()
        lifecycle = LifecycleRegistry(lifecycleOwner)
        viewModel.getData().observeForever(observer)

    }


    @Test
    fun testNull() {
        Mockito.`when`(viewModel.initRetrofit()).thenReturn(null)
        assertNotNull(viewModel.getData())
        assertTrue(viewModel.getData().hasObservers())
    }

    @Test
    fun fetchUserRepositories_positiveResponse() {
        // Mock API response
//        Mockito.`when`(this.api.getRepositories(ArgumentMatchers.anyString())).thenAnswer {
//            return@thenAnswer Maybe.just(ArgumentMatchers.anyList<Repository>())
//        }
//        // Attacch fake observer
//        val observer = mock(Observer::class.java) as Observer<LiveDataResult<List<Repository>>>
//        this.mainViewModel.repositoriesLiveData.observeForever(observer)
//        // Invoke
//        this.mainViewModel.fetchUserRepositories(ArgumentMatchers.anyString())
//        // Verify
//        assertNotNull(this.viewModel.repositoriesLiveData.value)
//        assertEquals(LiveDataResult.Status.SUCCESS, this.viewModel.repositoriesLiveData.value?.status)
    }

}