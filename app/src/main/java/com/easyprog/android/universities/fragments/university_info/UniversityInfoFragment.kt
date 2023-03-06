package com.easyprog.android.universities.fragments.university_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easyprog.android.universities.R
import com.easyprog.android.universities.activity.MainActivity
import com.easyprog.android.universities.databinding.FragmentUniversityInfoBinding
import com.easyprog.android.universities.fragments.BaseFragment

private const val ARG_UNIVERSITY_ID = "id"

class UniversityInfoFragment : BaseFragment<FragmentUniversityInfoBinding>(FragmentUniversityInfoBinding::inflate) {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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