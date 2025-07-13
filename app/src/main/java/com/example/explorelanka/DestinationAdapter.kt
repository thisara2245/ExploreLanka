package com.example.explorelanka

import Destination
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DestinationAdapter(
    private val context: Context,
    private val destinationList: List<Destination>,
    private val onItemClick: (Destination) -> Unit
) : RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder>() {

    private var filteredList: MutableList<Destination> = destinationList.toMutableList()

    class DestinationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val destinationIcon: ImageView = itemView.findViewById(R.id.destinationIcon)
        val destinationTitle: TextView = itemView.findViewById(R.id.destinationTitle)
        val destinationType: TextView = itemView.findViewById(R.id.destinationType)
        val destinationDescription: TextView = itemView.findViewById(R.id.destinationDescription)
        val destinationLocation: TextView = itemView.findViewById(R.id.destinationLocation)
        val bookmarkIcon: ImageView = itemView.findViewById(R.id.bookmarkIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destination, parent, false)
        return DestinationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        val destination = filteredList[position]

        holder.destinationIcon.setImageResource(destination.imageResId)
        holder.destinationTitle.text = destination.title
        holder.destinationType.text = destination.type
        holder.destinationDescription.text = destination.description
        holder.destinationLocation.text = destination.location

        val isFavorited = FavoriteManager.getFavorites().any { it.id == destination.id }

        holder.bookmarkIcon.setImageResource(
            if (isFavorited) R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark_border
        )

        holder.bookmarkIcon.setOnClickListener {
            if (isFavorited) {
                FavoriteManager.removeFromFavorites(destination)
                Toast.makeText(context, "${destination.title} removed from favorites", Toast.LENGTH_SHORT).show()
            } else {
                FavoriteManager.addToFavorites(destination)
                Toast.makeText(context, "${destination.title} added to favorites", Toast.LENGTH_SHORT).show()
            }
            notifyItemChanged(position)
        }


        holder.itemView.setOnClickListener {
            onItemClick(destination)
        }
    }

    override fun getItemCount(): Int = filteredList.size

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            destinationList.toMutableList()
        } else {
            destinationList.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.location.contains(query, ignoreCase = true) ||
                        it.type.contains(query, ignoreCase = true)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }
}
