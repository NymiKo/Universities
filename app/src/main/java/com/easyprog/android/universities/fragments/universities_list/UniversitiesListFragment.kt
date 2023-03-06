package com.easyprog.android.universities.fragments.universities_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.easyprog.android.universities.R
import com.easyprog.android.universities.activity.MainActivity
import com.easyprog.android.universities.adapters.UniversitiesListAdapter
import com.easyprog.android.universities.adapters.UniversityActionListener
import com.easyprog.android.universities.databinding.FragmentUniversitiesListBinding
import com.easyprog.android.universities.fragments.BaseFragment
import com.easyprog.android.universities.fragments.university_info.UniversityInfoFragment
import com.easyprog.android.universities.models.University
import com.easyprog.android.universities.utils.openFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class UniversitiesListFragment :
    BaseFragment<FragmentUniversitiesListBinding>(FragmentUniversitiesListBinding::inflate) {

    private val list = mutableListOf<University>()
    private lateinit var _adapter: UniversitiesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        loadUniversities()
    }

    private fun loadUniversities() {
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.mapNotNull {
                    list.add(it.getValue<University>()!!)
                }
                setupRecyclerView()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        if (list.isEmpty()) {
            _db.child("universities").addListenerForSingleValueEvent(valueEventListener)
        } else {
            setupRecyclerView()
        }
    }

    private fun setupRecyclerView() {
        val actionListener = object : UniversityActionListener{
            override fun onUniversityClick(idUniversity: Int) {
                openUniversityInfoFragment(idUniversity)
            }
        }
        _adapter = UniversitiesListAdapter(actionListener).apply {
            universitiesList = list
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