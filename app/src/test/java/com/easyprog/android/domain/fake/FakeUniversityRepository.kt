package com.easyprog.android.domain.fake

import com.easyprog.android.data.Result
import com.easyprog.android.data.models.University
import com.easyprog.android.data.models.UniversityInfo
import com.easyprog.android.domain.UniversityRepository

class FakeUniversityRepository: UniversityRepository {

    private var universityList: List<University> = emptyList()
    private var universityInfo: UniversityInfo = UniversityInfo()
    var error: Boolean = false

    fun setUniversityList(universityList: List<University>) {
        this.universityList = universityList
    }

    fun setUniversityInfo(universityInfo: UniversityInfo) {
        this.universityInfo = universityInfo
    }

    override suspend fun getUniversitiesList(): Result<List<University>> {
        return if(!error) {
            Result.SUCCESS(universityList)
        } else {
            Result.ERROR(Exception("Test error"))
        }
    }

    override suspend fun getUniversityInfo(id: Int): Result<UniversityInfo> {
        return if(!error) {
            Result.SUCCESS(universityInfo)
        } else {
            Result.ERROR(Exception("Test error"))
        }
    }
}