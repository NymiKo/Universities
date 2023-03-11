package com.easyprog.android.universities.fragments.university_info

import android.app.StatusBarManager
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.easyprog.android.data.Result
import com.easyprog.android.data.models.UniversityInfo
import com.easyprog.android.universities.R
import com.easyprog.android.universities.databinding.FragmentUniversityInfoBinding
import com.easyprog.android.universities.fragments.BaseFragment
import com.easyprog.android.universities.utils.fromHtmlToString
import com.easyprog.android.universities.utils.load
import com.easyprog.android.universities.utils.showSnackbar

private const val ARG_UNIVERSITY_ID = "id"

class UniversityInfoFragment :
    BaseFragment<FragmentUniversityInfoBinding>(FragmentUniversityInfoBinding::inflate) {

    private val idUniversity: Int get() = requireArguments().getInt(ARG_UNIVERSITY_ID)

    private val viewModel: UniversityInfoViewModel by lazy { ViewModelProvider(this)[UniversityInfoViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUniversityInfo(idUniversity)
        loadContent()
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
//        binding.collapsingToolbar.apply {
//            title = getString(R.string.app_name)
//        }
    }

    private fun loadContent() {
        viewModel.viewState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.SUCCESS -> {
                    setUniversityInfo(result.data)
                    binding.progress.visibility = View.GONE
                }
                is Result.ERROR -> {
                    binding.progress.visibility = View.GONE
                    showSnackbar(binding.root, R.string.error_data_loading)
                }
                Result.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setUniversityInfo(university: UniversityInfo) {
        binding.apply {
            imageUniversity.load(university.image)
            collapsingToolbar.title = university.name
            //textUniversityName.text = university.name
            textUniversityLocation.text = getString(R.string.location, university.location)
            textEntrancePointsBySpecialty.text = university.scores.fromHtmlToString()
            textBudgetPlaces.text = university.budget_places.fromHtmlToString()
            textDormitory.text = university.dormitory.fromHtmlToString()
            textDateOfApplicationSubmission.text = university.date_application.fromHtmlToString()
            scrollContentView.visibility = View.VISIBLE
        }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            UniversityInfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_UNIVERSITY_ID, id)
                }
            }
    }
}