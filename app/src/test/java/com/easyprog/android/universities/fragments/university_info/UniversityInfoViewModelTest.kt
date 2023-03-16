package com.easyprog.android.universities.fragments.university_info

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.easyprog.android.data.Result
import com.easyprog.android.data.models.UniversityInfo
import com.easyprog.android.domain.fake.FakeUniversityRepository
import com.easyprog.android.universities.CoroutineTestRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class UniversityInfoViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private var fakeRepository = FakeUniversityRepository()

    private lateinit var viewModel: UniversityInfoViewModel

    @Before
    fun setUp() {
        viewModel = UniversityInfoViewModel(fakeRepository)
    }

    @Test
    fun `getUniversityInfo should set view state to loading and then to success`() = runTest {
        val universityId = 1
        val universityInfo = UniversityInfo("Test University")
        val viewStateValues = mutableListOf<Result<UniversityInfo>>()
        viewModel.viewState.observeForever { viewStateValues.add(it) }

        fakeRepository.setUniversityInfo(universityInfo)
        viewModel.getUniversityInfo(universityId)

        val expectedViewStateValues = listOf(Result.LOADING, Result.SUCCESS(universityInfo))
        Assert.assertEquals(expectedViewStateValues, viewStateValues)
    }

    @Test
    fun `getUniversityInfo should set view state to loading and then to error if an exception is thrown`() =
        runTest {
            val universityId = 1
            val errorMsg = "Test error"
            val exception = Exception(errorMsg)
            val viewStateValues = mutableListOf<Result<UniversityInfo>>()
            viewModel.viewState.observeForever { viewStateValues.add(it) }

            fakeRepository.error = true
            viewModel.getUniversityInfo(universityId)

            val expectedViewStateValues = arrayListOf(Result.LOADING, Result.ERROR(exception))
            Assert.assertEquals(expectedViewStateValues, viewStateValues)
        }
}