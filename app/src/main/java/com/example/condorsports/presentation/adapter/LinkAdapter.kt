package com.example.condorsports.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.condorsports.R

/**
 * Adapter for the team links (Facebook, Twitter, Youtube)
 */
class LinkAdapter(private val links: List<String>, private val onClickListener:(String) -> Unit)
    : RecyclerView.Adapter<LinkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LinkViewHolder(layoutInflater.inflate(R.layout.item_link, parent,false))
    }

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        val link = links[position]
        holder.bind(link,onClickListener)
    }

    override fun getItemCount(): Int = links.size
}