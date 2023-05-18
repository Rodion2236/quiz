package com.rodion2236.quiz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rodion2236.quiz.R
import com.rodion2236.quiz.adapters.WallpaperAdapter
import com.rodion2236.quiz.data.Wallpapers
import com.rodion2236.quiz.databinding.FragmentWallpaperBinding

class WallpaperFragment : Fragment() {

private lateinit var binding: FragmentWallpaperBinding

private lateinit var recyclerView: RecyclerView
private lateinit var wallpapersList: ArrayList<Wallpapers>
private lateinit var wallpaperAdapter: WallpaperAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWallpaperBinding
            .inflate(inflater, container, false)

        init()

        return binding.root
    }

    private fun init() {
        recyclerView = binding.rvWallpaper
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        wallpapersList = ArrayList()
        addDataToList()

        wallpaperAdapter = WallpaperAdapter(wallpapersList)
        recyclerView.adapter = wallpaperAdapter
    }

    private fun addDataToList() {
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_car, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_cat, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_earth, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_flower, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_android_rain, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_hz_chto_eto, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_railway, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_rock, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_sunset, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_tiger, "Приобрести за 10"))

        //повтор цикла
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_car, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_cat, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_earth, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_flower, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_android_rain, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_hz_chto_eto, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_railway, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_rock, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_sunset, "Приобрести за 10"))
        wallpapersList.add(Wallpapers(R.drawable.wallpaper_tiger, "Приобрести за 10"))
    }
}