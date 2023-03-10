package com.easyprog.android.universities.fragments.universities_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.data.FirebaseDatabase
import com.easyprog.android.data.Result
import com.easyprog.android.data.models.University
import com.easyprog.android.domain.UniversitiesListRepository
import com.easyprog.android.domain.implementation.UniversitiesListRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UniversitiesListViewModel(): ViewModel() {

    private val repository: UniversitiesListRepository = UniversitiesListRepositoryImpl(
        FirebaseDatabase()
    )

    private val _universitiesList = MutableLiveData<List<University>>()
    val universitiesList: LiveData<List<University>> = _universitiesList

    fun getUniversitiesList() {
        viewModelScope.launch {
            when(val result = repository.getUniversitiesList()) {
                is Result.SUCCESS -> _universitiesList.value = result.data
                is Result.ERROR -> TODO()
                Result.LOADING -> TODO()
            }
        }
    }

}