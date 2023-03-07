package com.easyprog.android.universities.fragments.university_info

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.text.HtmlCompat
import com.easyprog.android.universities.R
import com.easyprog.android.universities.activity.MainActivity
import com.easyprog.android.universities.databinding.FragmentUniversityInfoBinding
import com.easyprog.android.universities.fragments.BaseFragment
import com.easyprog.android.universities.models.University
import com.easyprog.android.universities.models.UniversityInfo
import com.easyprog.android.universities.utils.fromHtmlToString
import com.easyprog.android.universities.utils.helpers.ValueEventListenerHelper
import com.easyprog.android.universities.utils.load
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

private const val ARG_UNIVERSITY_ID = "id"

class UniversityInfoFragment : BaseFragment<FragmentUniversityInfoBinding>(FragmentUniversityInfoBinding::inflate) {

    private val idUniversity: Int get() = requireArguments().getInt(ARG_UNIVERSITY_ID)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadContent()
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun loadContent() {
        val valueEventListener = ValueEventListenerHelper { snapshot ->
            val university = snapshot.getValue<UniversityInfo>()
            binding.apply {
                imageUniversity.load(university?.image)
                textUniversityName.text = university?.name
                textUniversityLocation.text = getString(R.string.location, university?.location)
                textEntrancePointsBySpecialty.text = university?.scores?.fromHtmlToString()
                textBudgetPlaces.text = university?.budget_places?.fromHtmlToString()
            }
        }
        _db.child("info").child(idUniversity.toString()).addListenerForSingleValueEvent(valueEventListener)
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