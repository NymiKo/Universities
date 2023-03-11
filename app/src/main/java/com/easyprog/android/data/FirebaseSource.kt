package com.easyprog.android.data

import com.easyprog.android.data.models.University
import com.easyprog.android.data.models.UniversityInfo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseSource {

    private val _db = FirebaseFirestore.getInstance()

    suspend fun getUniversitiesList(): Result<List<University>> {
        val snapshot = _db.collection("universities").orderBy("id").get().await()
        return if (!snapshot.isEmpty) {
            Result.SUCCESS(snapshot.toObjects(University::class.java))
        } else {
            Result.ERROR(Exception("No data"))
        }
    }

    suspend fun getUniversityInfo(id: Int): Result<UniversityInfo> {
        val snapshot = _db.collection("info").document(id.toString()).get().await()
        return try {
            Result.SUCCESS(snapshot.toObject(UniversityInfo::class.java)!!)
        } catch (e: Exception) {
            Result.ERROR(e)
        }
    }

}