package com.easyprog.android.universities.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.easyprog.android.universities.R
import com.easyprog.android.universities.databinding.ActivityMainBinding
import com.easyprog.android.universities.fragments.universities_list.UniversitiesListFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Universities)
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = UniversitiesListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}