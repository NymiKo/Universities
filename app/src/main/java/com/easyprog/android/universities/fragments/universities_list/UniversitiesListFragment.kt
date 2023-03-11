package com.easyprog.android.universities.fragments.universities_list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.easyprog.android.data.Result
import com.easyprog.android.universities.R
import com.easyprog.android.universities.activity.MainActivity
import com.easyprog.android.universities.adapters.UniversitiesListAdapter
import com.easyprog.android.universities.adapters.UniversityActionListener
import com.easyprog.android.universities.databinding.FragmentUniversitiesListBinding
import com.easyprog.android.universities.fragments.BaseFragment
import com.easyprog.android.universities.fragments.university_info.UniversityInfoFragment
import com.easyprog.android.data.models.University
import com.easyprog.android.universities.utils.openFragment
import com.easyprog.android.universities.utils.showSnackbar

class UniversitiesListFragment :
    BaseFragment<FragmentUniversitiesListBinding>(FragmentUniversitiesListBinding::inflate) {

    private lateinit var _adapter: UniversitiesListAdapter

    private val viewModel: UniversitiesListViewModel by lazy { ViewModelProvider(this)[UniversitiesListViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUniversitiesList()
        setupToolbar()
        loadUniversities()
    }

    private fun loadUniversities() {
        viewModel.viewState.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.SUCCESS -> {
                    setupRecyclerView(result.data)
                    binding.progress.visibility = View.GONE
                }
                is Result.ERROR -> {
                    binding.progress.visibility = View.GONE
                    showSnackbar(binding.root, R.string.error_data_loading)
                }
                Result.LOADING -> binding.progress.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView(universityList: List<University>) {
        val actionListener = object : UniversityActionListener{
            override fun onUniversityClick(idUniversity: Int) {
                openUniversityInfoFragment(idUniversity)
            }
        }
        _adapter = UniversitiesListAdapter(actionListener).apply {
            universitiesList = universityList
        }
        binding.recyclerViewUniversities.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = _adapter
        }
    }

    private fun openUniversityInfoFragment(id: Int) {
        val fragment = UniversityInfoFragment.newInstance(id)
        (requireActivity() as MainActivity).openFragment(fragment)
    }

    private fun setupToolbar() {
        binding.collapsingToolbar.apply {
            title = getString(R.string.app_name)
        }
    }

    override fun onDestroyView() {
        binding.recyclerViewUniversities.adapter = null
        super.onDestroyView()
    }
}