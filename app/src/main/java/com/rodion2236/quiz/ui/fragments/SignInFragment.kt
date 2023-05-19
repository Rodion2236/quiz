package com.rodion2236.quiz.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.rodion2236.quiz.databinding.FragmentSignInBinding
import com.rodion2236.quiz.db.DbProfile
import com.rodion2236.quiz.profile.CurrentProfile.Companion.instance
import com.rodion2236.quiz.profile.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.navigation.fragment.findNavController


class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private var profile: Profile? = null
    private lateinit var name: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding
            .inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSignIn.setOnClickListener {
            saveName()
        }
    }

    private fun saveName() {
        name = binding.editWelcome.text.toString().trim()
        if (name.isEmpty()) {
            return
        }

        viewLifecycleOwner.lifecycleScope
            .launch(Dispatchers.IO) {
            val profileDao = DbProfile.getInstance(requireContext())?.appDB?.profileDao()

            val existingUser = profileDao?.getUserByName(name)

            if (existingUser != null) {
                profile = existingUser
                instance?.currentProfile = profile
            } else {
                profile = Profile(name)
                profileDao?.insert(profile)
                instance?.currentProfile = profile
            }
            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), "Вход", Toast.LENGTH_LONG).show()
                val action = SignInFragmentDirections.actionSignInFragmentToHomeFragment(name = name)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}