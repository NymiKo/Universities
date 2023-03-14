package com.easyprog.android

import android.app.Application
import com.easyprog.android.data.FirebaseSource
import com.easyprog.android.domain.UniversityRepository
import com.easyprog.android.domain.implementation.UniversityRepositoryImpl
import com.google.firebase.FirebaseApp

class App: Application() {

    lateinit var repository: UniversityRepository

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(applicationContext)
        repository = UniversityRepositoryImpl(FirebaseSource())
    }

}