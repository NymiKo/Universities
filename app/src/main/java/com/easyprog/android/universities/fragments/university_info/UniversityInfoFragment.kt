package com.easyprog.android.universities.fragments.university_info

import android.os.Bundle
import android.text.Spanned
import android.view.View
import androidx.fragment.app.viewModels
import com.easyprog.android.data.Result
import com.easyprog.android.data.models.UniversityInfo
import com.easyprog.android.universities.R
import com.easyprog.android.universities.databinding.FragmentUniversityInfoBinding
import com.easyprog.android.universities.fragments.base.BaseFragment
import com.easyprog.android.universities.fragments.base.factory
import com.easyprog.android.universities.fragments.reception_committee.ReceptionCommitteeFragment
import com.easyprog.android.universities.utils.fromHtmlToString
import com.easyprog.android.universities.utils.load
import com.easyprog.android.universities.utils.showSnackbar

class UniversityInfoFragment :
    BaseFragment<FragmentUniversityInfoBinding>(FragmentUniversityInfoBinding::inflate) {

    private val idUniversity: Int get() = requireArguments().getInt(ARG_UNIVERSITY_ID)

    private val viewModel: UniversityInfoViewModel by viewModels { factory() }
    private var receptionCommittee: String = ""
    private var phone: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUniversityInfo(idUniversity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadContent()
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        createPopupMenu()
    }

    private fun createPopupMenu() {
        binding.toolbar.inflateMenu(R.menu.university_info_menu)
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.opening_hours_admissions_committee -> {
                    val bottomSheetFragment = ReceptionCommitteeFragment.newInstance(receptionCommittee, phone)
                    bottomSheetFragment.show(childFragmentManager, "ReceptionCommitteeFragment")
                    true
                }
                else -> false
            }
        }
    }

    private fun loadContent() {
        viewModel.viewState.observe(viewLifecycleOwner) { result ->
            handleViewState(result)
        }
    }

    private fun handleViewState(result: Result<UniversityInfo>) {
        when (result) {
            is Result.SUCCESS -> {
                setUniversityInfo(result.data)
                hideProgressbar()
                showScrollContent()
            }
            is Result.ERROR -> {
                hideProgressbar()
                showSnackbar(requireView(), R.string.error_data_loading)
            }
            Result.LOADING -> {
                showProgressbar()
            }
        }
    }

    private fun setUniversityInfo(university: UniversityInfo) {
        binding.apply {
            imageUniversity.load(university.image)
            collapsingToolbar.title = university.name
            textUniversityLocation.text = getString(R.string.location, university.location)
            textEntrancePointsBySpecialty.text = university.scores.fromHtmlToString()
            textBudgetPlaces.text = university.budget_places.fromHtmlToString()
            textDormitory.text = university.dormitory.fromHtmlToString()
            textDateOfApplicationSubmission.text = university.date_application.fromHtmlToString()
            phone = university.phone
            receptionCommittee = university.reception_committee
        }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun showProgressbar() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideProgressbar() {
        binding.progress.visibility = View.GONE
    }

    private fun showScrollContent() {
        binding.scrollContentView.visibility = View.VISIBLE
    }

    companion object {
        private const val ARG_UNIVERSITY_ID = "id"

        @JvmStatic
        fun newInstance(id: Int) =
            UniversityInfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_UNIVERSITY_ID, id)
                }
            }
    }
}