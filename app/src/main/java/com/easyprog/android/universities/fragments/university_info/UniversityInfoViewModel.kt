package com.easyprog.android.universities.fragments.university_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.data.FirebaseSource
import com.easyprog.android.data.Result
import com.easyprog.android.data.models.UniversityInfo
import com.easyprog.android.domain.UniversityRepository
import com.easyprog.android.domain.implementation.UniversityRepositoryImpl
import kotlinx.coroutines.launch

class UniversityInfoViewModel(private val repository: UniversityRepository) : ViewModel() {

    private val _viewState = MutableLiveData<Result<UniversityInfo>>(Result.LOADING)
    val viewState: LiveData<Result<UniversityInfo>> = _viewState

    fun getUniversityInfo(id: Int) {
        viewModelScope.launch {
            val result = repository.getUniversityInfo(id)
            _viewState.value = result
        }
    }
}