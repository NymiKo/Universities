package com.easyprog.android.universities.fragments.reception_committee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easyprog.android.universities.R
import com.easyprog.android.universities.databinding.FragmentReceptionCommitteeBinding
import com.easyprog.android.universities.utils.fromHtmlToString
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReceptionCommitteeFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentReceptionCommitteeBinding? = null
    private val binding get() = _binding!!

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    private val receptionCommittee get() = requireArguments().getString(ARG_RECEPTION_COMMITTEE)
    private val phone get() = requireArguments().getString(ARG_PHONE)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReceptionCommitteeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            textReceptionCommitteeTime.text = receptionCommittee?.fromHtmlToString()
            textReceptionCommitteePhone.text = phone?.fromHtmlToString()
        }
    }

    companion object {
        private const val ARG_RECEPTION_COMMITTEE = "reception_committee"
        private const val ARG_PHONE = "phone"

        @JvmStatic
        fun newInstance(reception_committee: String, phone: String) = ReceptionCommitteeFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_RECEPTION_COMMITTEE, reception_committee)
                putString(ARG_PHONE, phone)
            }
        }
    }
}