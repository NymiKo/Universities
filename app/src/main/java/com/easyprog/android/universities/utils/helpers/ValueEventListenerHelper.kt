package com.easyprog.android.universities.utils.helpers

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ValueEventListenerHelper(val block:(snapshot: DataSnapshot) -> Unit): ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot) {
        block(snapshot)
    }

    override fun onCancelled(error: DatabaseError) {

    }
}