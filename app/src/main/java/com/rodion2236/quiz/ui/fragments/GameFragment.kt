package com.rodion2236.quiz.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rodion2236.quiz.R
import com.rodion2236.quiz.adapters.GameAdapter
import com.rodion2236.quiz.data.GameLevel
import com.rodion2236.quiz.databinding.FragmentGameBinding
import com.rodion2236.quiz.profile.CurrentProfile.Companion.instance

class GameFragment : Fragment(), GameAdapter.onClickListener {

    private lateinit var binding: FragmentGameBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var gameLevelList: ArrayList<GameLevel>
    private lateinit var gameAdapter: GameAdapter

    var name = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding
            .inflate(inflater, container, false)

        binding.ablBack.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, HomeFragment())?.commit()
        }
        init()
        val profile = instance!!.currentProfile
        name = profile!!.name

        val scoreText = resources.getString(R.string.score, profile.score)
        binding.scoreGameLevel.text = scoreText


        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val profile = instance!!.currentProfile
        val scoreText = resources.getString(R.string.score, profile?.score)
        binding.scoreGameLevel.text = scoreText
    }

    private fun init() {
        recyclerView = binding.rvCategoryGame
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        gameLevelList = ArrayList()
        addDataToList()

        gameAdapter = GameAdapter(gameLevelList, this@GameFragment)
        recyclerView.adapter = gameAdapter
    }

    private fun addDataToList() {
        gameLevelList.add(GameLevel(R.drawable.easy_icon, "Легкий уровень"))
        gameLevelList.add(GameLevel(R.drawable.medim_icon, "Средний уровень"))
        gameLevelList.add(GameLevel(R.drawable.hard_icon, "Тяжелый уровень"))
    }

    override fun onClick(position: Int, gameLevel: GameLevel) {
        val args = Bundle()
        if (position == 0) {
            args.putString("level", "easy")
        } else if (position == 1) {
            args.putString("level", "medium")
        } else if (position == 2) {
            args.putString("level", "hard")
        } else {
            return
        }

        QuestionFragment().arguments = args
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, QuestionFragment())?.commit()
    }
}