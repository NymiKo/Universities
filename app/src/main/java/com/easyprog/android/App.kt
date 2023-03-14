package com.easyprog.android

import android.app.Application
import com.easyprog.android.data.FirebaseSource
import com.easyprog.android.domain.UniversityRepository
import com.easyprog.android.domain.implementation.UniversityRepositoryImpl

class App: Application() {

    val repository = UniversityRepositoryImpl(FirebaseSource())

}