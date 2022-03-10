package com.example.wordspickapps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordspickapps.databinding.ActivityMainBinding

/**
 * main activity and entry point for the app. dispplay a RecyclerView of letters
 */

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        //sets the LinearLayoutManager of the recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LetterAdapter()
    }
}