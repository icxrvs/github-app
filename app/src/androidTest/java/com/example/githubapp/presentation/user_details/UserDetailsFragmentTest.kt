package com.example.githubapp.presentation.user_details

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.githubapp.launchFragmentInHiltContainer
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import com.example.githubapp.R
import com.example.githubapp.waitFor

@RunWith(AndroidJUnit4::class)
class UserDetailsFragmentTest{
    private val username = "icarusdevv"

    @Test
    fun whenScreenLoads_progressBarShouldBeVisible() {
        val bundle = UserDetailsFragmentArgs(username).toBundle()
        launchFragmentInHiltContainer<UserDetailsFragment>(fragmentArgs = bundle)
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
    }

    @Test
    fun whenGetData_cardViewWithInfoShouldBeVisible() {
        val bundle = UserDetailsFragmentArgs(username).toBundle()
        launchFragmentInHiltContainer<UserDetailsFragment>(fragmentArgs = bundle)

        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))

        onView(ViewMatchers.isRoot()).perform(waitFor(1000))

        onView(withId(R.id.card_view)).check(matches(isDisplayed()))

        onView(ViewMatchers.isRoot()).perform(waitFor(1000))

        onView(withId(R.id.user_name)).check(matches(isDisplayed()))

        onView(withId(R.id.user_bio)).check(matches(isDisplayed()))

        onView(withId(R.id.user_name)).check(matches(withText("ICARO CAMPOS ALVES")))

        onView(withId(R.id.user_bio)).check(matches(withText("Android | iOS Developer")))

        onView(ViewMatchers.isRoot()).perform(waitFor(500))
    }


    @Test
    fun givenSuccessfulApiCall_repositoriesListShouldBeDisplayed() {
        val bundle = UserDetailsFragmentArgs(username).toBundle()
        launchFragmentInHiltContainer<UserDetailsFragment>(fragmentArgs = bundle)

        onView(ViewMatchers.isRoot()).perform(waitFor(500))

        onView(withId(R.id.card_view)).check(matches(isDisplayed()))

        onView(ViewMatchers.isRoot()).perform(waitFor(500))

        onView(withId(R.id.rv_repositories)).check(matches(isDisplayed()))

        onView(ViewMatchers.isRoot()).perform(waitFor(500))
    }
}