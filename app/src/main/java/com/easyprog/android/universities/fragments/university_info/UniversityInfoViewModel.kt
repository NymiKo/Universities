package com.easyprog.android.universities.fragments.university_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.android.data.FirebaseSource
import com.easyprog.android.data.Result
import com.easyprog.android.data.models.UniversityInfo
import com.easyprog.android.domain.implementation.UniversityRepositoryImpl
import kotlinx.coroutines.launch

class UniversityInfoViewModel : ViewModel() {

    private val repository = UniversityRepositoryImpl(FirebaseSource())

    private val _viewState = MutableLiveData<Result<UniversityInfo>>()
    val viewState: LiveData<Result<UniversityInfo>> = _viewState

    fun getUniversityInfo(id: Int) {
        _viewState.value = Result.LOADING
        viewModelScope.launch {
            _viewState.value = repository.getUniversityInfo(id)
        }
    }
}