package com.rodion2236.quiz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodion2236.quiz.R
import com.rodion2236.quiz.data.SelectHome

class HomeAdapter(private val homeList:ArrayList<SelectHome>):
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_start_home)
        val textView: TextView = itemView.findViewById(R.id.title_start_home)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val selectHome = homeList[position]
        holder.imageView.setImageResource(selectHome.image)
        holder.textView.text = selectHome.title
    }
}