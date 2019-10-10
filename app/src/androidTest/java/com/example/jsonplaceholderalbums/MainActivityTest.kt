package com.example.jsonplaceholderalbums

import android.os.Looper
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jsonplaceholderalbums.view.MainActivity
import com.example.jsonplaceholderalbums.viewmodel.CustomViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun appLaunchesSuccessfully() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun onLaunchCheckRecyclerViewIsDisplayed() {
        // 1
        ActivityScenario.launch(MainActivity::class.java)

        // 2
        onView(withId(R.id.recycler_view))
            // 3
            .check(matches(isDisplayed()))
    }

    @Test
    fun onLaunchAppNameIsDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withText(R.string.app_name))
            .check(matches(isDisplayed()))
    }

    @Test
    fun recyclerViewScrollsSuccessful(){
        Looper.prepare()
        ActivityScenario.launch(MainActivity::class.java)

        //val itemCount = MainActivity().recycler_view.adapter!!.itemCount-1
        onView(withId(R.id.recycler_view))
            .perform(RecyclerViewActions.scrollToPosition<CustomViewHolder>(1))
    }

    @Test
    fun listViewInRecyclerviewClick(){
        ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(1000)
        onView(withId(R.id.recycler_view))
            .perform(RecyclerViewActions.scrollToPosition<CustomViewHolder>(1))
            .check(matches(hasDescendant(withText("aperiam odio fugiat"))))
    }


}