package com.rodion2236.quiz.adapters

import android.app.WallpaperManager
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodion2236.quiz.R
import com.rodion2236.quiz.data.Wallpapers
import okio.IOException

class WallpaperAdapter(private val wallpaperList:ArrayList<Wallpapers>):
    RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>() {

    inner class WallpaperViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.wallpaper_image)
        val textView: TextView = itemView.findViewById(R.id.wallpaper_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wallpaper, parent, false)
        val holder = WallpaperViewHolder(view)

        holder.itemView.setOnClickListener {
            val builder = AlertDialog.Builder(parent.context)
            builder.apply {
                setTitle("Установить на рабочий стол?")
                setPositiveButton("Да") { _, _ ->
                    val bitmap = (holder.imageView.drawable as BitmapDrawable).bitmap
                    val wallpaperManager = WallpaperManager.getInstance(parent.context)
                    try {
                        wallpaperManager.setBitmap(bitmap)
                        Toast.makeText(parent.context, "Обои установлены", Toast.LENGTH_SHORT).show()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                setNegativeButton("Нет") { _, _ -> }
            }
            builder.create().show()
        }
        return WallpaperViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wallpaperList.size
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        val wallpaper = wallpaperList[position]
        Glide.with(holder.imageView.context)
            .load(wallpaper.image)
            .into(holder.imageView)
        holder.textView.text = wallpaper.title
    }
}