package com.easyprog.android.universities.fragments.universities_list

import android.os.Bundle
import android.util.Log
import android.view.View
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
        loadUniversities()
    }

    private fun loadUniversities() {
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.mapNotNull {
                    list.add(it.getValue<University>()!!)
                }
                Log.e("DATA", list.toString())
                setupRecyclerView(list.toList())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        _db.child("universities").addListenerForSingleValueEvent(valueEventListener)
    }

    private fun setupRecyclerView(list: List<University>) {
        _adapter = UniversitiesListAdapter(object : UniversityActionListener{
            override fun onUniversityClick(idUniversity: Int) {
                openUniversityInfoFragment(idUniversity)
            }
        }).apply {
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

    override fun onDestroyView() {
        binding.recyclerViewUniversities.adapter = null
        super.onDestroyView()
    }
}