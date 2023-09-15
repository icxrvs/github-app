package com.example.githubapp.presentation.list_user

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import com.example.githubapp.R
import com.example.githubapp.launchFragmentInHiltContainer
import com.example.githubapp.waitFor
@RunWith(AndroidJUnit4::class)
class UsersFragmentTest {

    @Test
    fun whenScreenAppears_shouldShowProgressBar_andShowUsersList() {
        launchFragmentInHiltContainer<UsersFragment>()
        onView(withId(R.id.progress_bar))
            .check(matches(isDisplayed()))

        onView(isRoot()).perform(waitFor(1000))

        onView(withId(R.id.rv_users))
            .check(matches(isDisplayed()))
    }


    @Test
    fun whenFabButtonAreClicked_opensAlertDialog_andInputAUserName() {
        launchFragmentInHiltContainer<UsersFragment>()

        onView(withId(R.id.fab_button)).perform(click())

        onView(withText(R.string.search_user))
            .check(matches(isDisplayed()))

        onView(withId(R.id.editText)).perform(typeText("icarusdevv"),  closeSoftKeyboard())
    }
}