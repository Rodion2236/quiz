package com.rodion2236.quiz.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.rodion2236.quiz.R
import com.rodion2236.quiz.profile.CurrentProfile.Companion.instance

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentProfile = instance?.currentProfile

        if (currentProfile != null) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
        } else {
            findNavController(R.id.nav_host_fragment).navigate(R.id.signInFragment)
        }
    }
}

//timer = object: CountDownTimer(30000, 1000) {
//    override fun onTick(timeM: Long) {
//        binding.timerTv.text = timeM.toString()
//    }