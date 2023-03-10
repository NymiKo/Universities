package com.easyprog.android.domain

import com.easyprog.android.data.Result
import com.easyprog.android.data.models.UniversityInfo

interface UniversityInfoRepository {
    suspend fun getUniversityInfo(id: Int): Result<UniversityInfo>
}