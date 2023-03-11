package com.easyprog.android.domain.implementation

import com.easyprog.android.data.FirebaseSource
import com.easyprog.android.domain.UniversitiesListRepository
import com.easyprog.android.data.models.University
import com.easyprog.android.data.Result

class UniversitiesListRepositoryImpl(private val firebaseSource: FirebaseSource): UniversitiesListRepository {
    override suspend fun getUniversitiesList() = firebaseSource.getUniversitiesList()
}