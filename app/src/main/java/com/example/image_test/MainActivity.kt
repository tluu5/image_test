package com.example.image_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.example.image_test.view.FragmentListMovie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (findViewById<FragmentContainerView>(R.id.container) != null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FragmentListMovie())
                .commit()
    }
}