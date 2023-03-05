package com.easyprog.android.universities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

abstract class BaseFragment<T : ViewBinding>(private val bindingInflater: (LayoutInflater) -> T) :
    Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    protected lateinit var _db: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = bindingInflater(inflater)
        _db = FirebaseDatabase.getInstance().reference
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}