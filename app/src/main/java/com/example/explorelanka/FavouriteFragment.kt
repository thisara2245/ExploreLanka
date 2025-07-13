package com.example.explorelanka

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Load favorites and set up adapter
        FavoriteManager.loadFavorites {
            val favorites = FavoriteManager.getFavorites()
            adapter = DestinationAdapter(requireContext(), favorites) { selectedDestination ->
                val intent = Intent(requireContext(), LocationDetailsActivity::class.java)
                intent.putExtra("title", selectedDestination.title)
                intent.putExtra("imageResId", selectedDestination.imageResId)
                intent.putExtra("description", "This is a favorite destination.")
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
