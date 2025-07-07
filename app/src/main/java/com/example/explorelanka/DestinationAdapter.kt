package com.example.explorelanka

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class DestinationAdapter(
    private val context: Context,
    private val destinationList: List<Destination>
) : RecyclerView.Adapter<DestinationAdapter.JobViewHolder>() {

    private var filteredList: MutableList<Destination> = destinationList.toMutableList()

    class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jobIcon: ImageView = itemView.findViewById(R.id.jobIcon)
        val jobTitle: TextView = itemView.findViewById(R.id.jobTitle)
        val jobType: TextView = itemView.findViewById(R.id.jobType)
        val jobSalary: TextView = itemView.findViewById(R.id.jobSalary)
        val jobLocation: TextView = itemView.findViewById(R.id.jobLocation)
        val bookmarkIcon: ImageView = itemView.findViewById(R.id.bookmarkIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_destination, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = filteredList[position]

        holder.jobIcon.setImageResource(job.iconResId)
        holder.jobTitle.text = job.title
        holder.jobType.text = job.type
        holder.jobSalary.text = job.salary
        holder.jobLocation.text = job.location

        holder.bookmarkIcon.setOnClickListener {
            FavoriteManager.addToFavorites(job)
            Toast.makeText(context, "${job.title} added to favorites", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = filteredList.size

    // ✅ Filter method
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
