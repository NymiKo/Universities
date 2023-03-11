package com.easyprog.android.domain.implementation

import com.easyprog.android.data.FirebaseSource
import com.easyprog.android.domain.UniversityInfoRepository

class UniversityInfoRepositoryImpl(private val firebaseSource: FirebaseSource) : UniversityInfoRepository {
    override suspend fun getUniversityInfo(id: Int) = firebaseSource.getUniversityInfo(id)
}