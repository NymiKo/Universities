package com.easyprog.android.universities

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineTestRule: TestWatcher() {

    private val testDispatcher = UnconfinedTestDispatcher()

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }

//    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
//        testCoroutineScope.runBlockingTest { block() }

}