package com.rodion2236.quiz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rodion2236.quiz.R
import com.rodion2236.quiz.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding
            .inflate(inflater, container, false)

        val button_reverse = binding.buttonResult

        button_reverse.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, GameFragment())?.commit()
        }

        return binding.root
    }
}