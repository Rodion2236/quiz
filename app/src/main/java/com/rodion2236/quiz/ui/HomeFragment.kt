package com.rodion2236.quiz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rodion2236.quiz.R
import com.rodion2236.quiz.adapters.HomeAdapter
import com.rodion2236.quiz.data.SelectHome
import com.rodion2236.quiz.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), HomeAdapter.onClickListener {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var selectHomeList: ArrayList<SelectHome>
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding
            .inflate(inflater, container, false)

        binding.ablExit.setOnClickListener {
            activity?.onBackPressed()
        }
        init()
        return binding.root
    }

    private fun init() {
        recyclerView = binding.rvCategoryStart
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        selectHomeList = ArrayList()
        addDataToList()

        homeAdapter = HomeAdapter(selectHomeList, this@HomeFragment)
        recyclerView.adapter = homeAdapter
    }

    private fun addDataToList() {
        selectHomeList.add(SelectHome(R.drawable.game_icon, "Начать игру"))
        selectHomeList.add(SelectHome(R.drawable.wallpaper_icon, "Купить картинку"))
    }

    override fun onClick(position: Int) {
        if (position==0) {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, GameFragment())?.commit()
        } else {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, WallpaperFragment())?.commit()
        }
    }
}