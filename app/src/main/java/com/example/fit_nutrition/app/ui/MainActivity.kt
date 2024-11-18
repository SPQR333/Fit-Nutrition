package com.example.fit_nutrition.app.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fit_nutrition.R
import com.example.fit_nutrition.databinding.ActivityMainBinding
import com.example.fit_nutrition.profile.ui.profile.ProfileFragment
import com.example.fit_nutrition.profile.ui.profile.ProfileViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {

        val fragment = ProfileFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frag_container, fragment)
                .commit()

        }
    }
}