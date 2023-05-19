package com.rodion2236.quiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rodion2236.quiz.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

//timer = object: CountDownTimer(30000, 1000) {
//    override fun onTick(timeM: Long) {
//        binding.timerTv.text = timeM.toString()
//    }