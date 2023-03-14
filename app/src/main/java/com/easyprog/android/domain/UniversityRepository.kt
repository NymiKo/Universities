package com.easyprog.android.domain

import com.easyprog.android.data.Result
import com.easyprog.android.data.models.University
import com.easyprog.android.data.models.UniversityInfo

interface UniversityRepository {
    suspend fun getUniversitiesList(): Result<List<University>>
    suspend fun getUniversityInfo(id: Int): Result<UniversityInfo>
}