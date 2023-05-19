package com.rodion2236.quiz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rodion2236.quiz.R
import com.rodion2236.quiz.data.GameLevel

class GameAdapter(private val gamesList:ArrayList<GameLevel>, private val listener:onClickListener):
    RecyclerView.Adapter<GameAdapter.GameLevelViewHolder>() {

    inner class GameLevelViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_game)
        val textView: TextView = itemView.findViewById(R.id.title_game)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                listener.onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameLevelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game, parent, false)
        return GameLevelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gamesList.size
    }

    override fun onBindViewHolder(holder: GameLevelViewHolder, position: Int) {
        val gamesLevel = gamesList[position]
        holder.imageView.setImageResource(gamesLevel.image)
        holder.textView.text = gamesLevel.title
    }

    interface onClickListener {
        fun onClick(position: Int)
    }
}