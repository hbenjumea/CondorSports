package com.example.condorsports.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.condorsports.databinding.ItemLinkBinding

/**
 * Viewholder for the team links (Facebook, Twitter, Youtube)
 */
class LinkViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemLinkBinding.bind(view)

    fun bind(link: String, onClickListener:(String) -> Unit){
        binding.tvLink.text = link

        itemView.setOnClickListener {onClickListener(link)}
    }
}