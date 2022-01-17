package com.example.condorsports.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.condorsports.data.model.Event
import com.example.condorsports.databinding.ItemEventBinding

/**
 * Viewholder for the next 5 events
 */
class EventViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemEventBinding.bind(view)

    fun bind(event: Event){
        binding.tvLeagueEvent.text = event.league
    }
}