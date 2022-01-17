package com.example.condorsports.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.condorsports.R
import com.example.condorsports.data.model.Team

/**
 * Adapter for the teams
 */
class TeamAdapter(private val teams: List<Team>, private val onClickListener:(Team) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TeamViewHolder(layoutInflater.inflate(R.layout.item_team, parent,false))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teams[position]
        holder.bind(team, onClickListener)
    }

    override fun getItemCount(): Int = teams.size
}