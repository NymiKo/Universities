package com.easyprog.android.domain.implementation

import com.easyprog.android.data.FirebaseSource
import com.easyprog.android.domain.UniversityRepository

class UniversityRepositoryImpl(private val firebaseSource: FirebaseSource): UniversityRepository {

    override suspend fun getUniversitiesList() = firebaseSource.getUniversitiesList()

    override suspend fun getUniversityInfo(id: Int) = firebaseSource.getUniversityInfo(id)
}