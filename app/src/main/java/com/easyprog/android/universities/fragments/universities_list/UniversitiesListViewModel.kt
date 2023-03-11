package com.easyprog.android.universities.fragments.universities_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.data.FirebaseSource
import com.easyprog.android.data.Result
import com.easyprog.android.data.models.University
import com.easyprog.android.domain.UniversitiesListRepository
import com.easyprog.android.domain.implementation.UniversitiesListRepositoryImpl
import kotlinx.coroutines.launch

typealias MutableLiveResult<T> = MutableLiveData<Result<T>>
typealias LiveResult<T> = LiveData<Result<T>>

class UniversitiesListViewModel : ViewModel() {

    private val repository: UniversitiesListRepository = UniversitiesListRepositoryImpl(
        FirebaseSource()
    )

    private val _viewState = MutableLiveResult<List<University>>()
    val viewState: LiveResult<List<University>> = _viewState

    fun getUniversitiesList() {
        _viewState.value = Result.LOADING
        viewModelScope.launch {
            _viewState.value = repository.getUniversitiesList()
        }
    }
}