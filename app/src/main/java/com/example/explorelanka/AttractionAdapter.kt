package com.example.explorelanka

import Attraction
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AttractionAdapter(
    private val context: Context,
    private val attractions: List<Attraction>
) : RecyclerView.Adapter<AttractionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.attractionName)
        val desc: TextView = view.findViewById(R.id.attractionDesc)
        val image: ImageView = view.findViewById(R.id.attractionImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_attraction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = attractions[position]
        holder.name.text = item.name
        holder.desc.text = item.description
        holder.image.setImageResource(item.imageResId)
    }

    override fun getItemCount(): Int = attractions.size
}
