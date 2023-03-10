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

typealias ResultList<T> = Result<List<T>>

class UniversitiesListViewModel : ViewModel() {

    private val repository: UniversitiesListRepository = UniversitiesListRepositoryImpl(
        FirebaseSource()
    )

    private val _dataState = MutableLiveData<ResultList<University>>()
    val dataState: LiveData<ResultList<University>> = _dataState

    fun getUniversitiesList() = viewModelScope.launch {
        _dataState.value = Result.LOADING
        _dataState.value = repository.getUniversitiesList()
    }
}