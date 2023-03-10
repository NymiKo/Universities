package com.easyprog.android.domain.implementation

import com.easyprog.android.data.FirebaseDatabase
import com.easyprog.android.domain.UniversitiesListRepository
import com.easyprog.android.data.models.University
import com.easyprog.android.data.Result

class UniversitiesListRepositoryImpl(private val firebaseDatabase: FirebaseDatabase): UniversitiesListRepository {
    override suspend fun getUniversitiesList(): Result<List<University>> {
        return firebaseDatabase.getUniversitiesList()
    }
}