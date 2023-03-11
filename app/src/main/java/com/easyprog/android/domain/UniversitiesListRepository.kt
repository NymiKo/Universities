package com.easyprog.android.domain

import com.easyprog.android.data.Result
import com.easyprog.android.data.models.University

interface UniversitiesListRepository {

    suspend fun getUniversitiesList(): Result<List<University>>

}