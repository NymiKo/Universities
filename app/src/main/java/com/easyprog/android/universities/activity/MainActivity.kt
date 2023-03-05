package com.easyprog.android.universities.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.easyprog.android.universities.R
import com.easyprog.android.universities.fragments.universities_list.UniversitiesListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Universities)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = UniversitiesListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }
}