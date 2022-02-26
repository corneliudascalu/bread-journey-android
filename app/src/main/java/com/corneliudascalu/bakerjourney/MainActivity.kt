package com.corneliudascalu.bakerjourney

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.corneliudascalu.bakerjourney.databinding.ActivityMainBinding
import com.corneliudascalu.bakerjourney.log.LogEntriesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            replace(R.id.container, LogEntriesFragment())
            addToBackStack(null)
        }
    }
}