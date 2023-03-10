package com.easyprog.android.data

import com.easyprog.android.data.models.University
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseDatabase {

    private var _db: FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun getUniversitiesList(): Result<List<University>> {
        val snapshot = _db.collection("universities").get().await()
        return if (!snapshot.isEmpty) {
            Result.SUCCESS(snapshot.toObjects(University::class.java))
        } else {
            Result.ERROR(Exception("No data"))
        }
    }

}