package com.example.myunit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getUser()

    }
}