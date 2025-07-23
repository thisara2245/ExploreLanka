package com.example.explorelanka

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoriteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var adapter: DestinationAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)

        recyclerView = view.findViewById(R.id.recyclerFavorites)

        // Use safe context
        val context = context ?: return view

        recyclerView.layoutManager = GridLayoutManager(context, 2)

        // Load favorites and set up adapter safely
        FavoriteManager.loadFavorites {
            // Ensure Fragment is still attached before updating UI
            if (!isAdded) return@loadFavorites

            val safeContext = context ?: return@loadFavorites
            val favorites = FavoriteManager.getFavorites()

            adapter = DestinationAdapter(safeContext, favorites) { selectedDestination ->
                val intent = Intent(safeContext, LocationDetailsActivity::class.java).apply {
                    putExtra("title", selectedDestination.title)
                    putExtra("imageResId", selectedDestination.imageResId)
                    putExtra("description", "This is a favorite destination.")
                }
                startActivity(intent)
            }

            recyclerView.adapter = adapter
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()
    }
}
