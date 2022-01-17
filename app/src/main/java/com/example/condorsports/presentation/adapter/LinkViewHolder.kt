package com.example.condorsports.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.condorsports.databinding.ItemLinkBinding

class LinkViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemLinkBinding.bind(view)

    fun bind(link: String, onClickListener:(String) -> Unit){
        binding.tvLink.text = link

        itemView.setOnClickListener {onClickListener(link)}
    }
}