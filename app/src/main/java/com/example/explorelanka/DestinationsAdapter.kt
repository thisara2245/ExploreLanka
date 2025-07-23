package com.example.explorelanka

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DestinationsAdapter(
    private val destinations: List<Destinations>,
    private val onItemClick: (Destinations) -> Unit
) : RecyclerView.Adapter<DestinationsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.destinationName)
        val costText: TextView = itemView.findViewById(R.id.destinationCost)
        val descText: TextView = itemView.findViewById(R.id.destinationDescription)
        val imageView: ImageView = itemView.findViewById(R.id.destinationImage)

        fun bind(destination: Destinations) {
            nameText.text = destination.name
            costText.text = "Cost/day/person: Rs. ${destination.costofonedayPerPerson}"
            descText.text = destination.description

            Glide.with(itemView.context)
                .load(destination.imgUrl)
                .placeholder(R.drawable.matara1)
                .error(R.drawable.abewela)
                .into(imageView)

            itemView.setOnClickListener {
                onItemClick(destination)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.destination_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = destinations[position]
        holder.bind(destination)
    }

    override fun getItemCount(): Int = destinations.size
}


