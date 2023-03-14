package com.easyprog.android.universities.fragments.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.easyprog.android.App
import com.easyprog.android.universities.adapters.UniversitiesListAdapter
import com.easyprog.android.universities.fragments.universities_list.UniversitiesListViewModel
import com.easyprog.android.universities.fragments.university_info.UniversityInfoViewModel

class ViewModelFactory(
    private val app: App
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass) {
            UniversitiesListViewModel::class.java -> {
                UniversitiesListViewModel(app.repository)
            }
            UniversityInfoViewModel::class.java -> {
                UniversityInfoViewModel(app.repository)
            }
            else -> {
                IllegalStateException("Unknown viewmodel class")
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)