package com.example.fit_nutrition

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fit_nutrition.databinding.ActivityMainBinding
import com.example.fit_nutrition.presentation.CalcFragment
import com.example.fit_nutrition.presentation.DataModel


class MainActivity : AppCompatActivity() {
lateinit var binding: ActivityMainBinding
private val dataModel: DataModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button : Button = findViewById(R.id.bt)
        val fragment = CalcFragment()
        button.setOnClickListener(){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frag_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
            dataModel.message.observe(this,{
                binding.bt.text = it
            })


        }
    }
}