package com.easyprog.android.universities.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.easyprog.android.universities.adapters.UniversitiesListAdapter.*
import com.easyprog.android.universities.databinding.ItemUniversityBinding
import com.easyprog.android.universities.models.University
import com.easyprog.android.universities.utils.load

class UniversitiesListAdapter: RecyclerView.Adapter<UniversitiesListViewHolder>() {

    var universitiesList: List<University> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversitiesListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUniversityBinding.inflate(layoutInflater, parent, false)
        return UniversitiesListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UniversitiesListViewHolder, position: Int) {
        val university = universitiesList[position]
        with(holder.binding) {
            imageUniversity.load(university.image)
            textNameUniversity.text = university.name
        }
    }

    override fun getItemCount() = universitiesList.size

    class UniversitiesListViewHolder(val binding: ItemUniversityBinding): RecyclerView.ViewHolder(binding.root)
}