package com.example.simplemovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simplemovieapp.fragments.popular.PopularMoveFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = supportFragmentManager.beginTransaction()
        fragment.add(R.id.rootFragment, PopularMoveFragment())
        fragment.commit();
    }
}