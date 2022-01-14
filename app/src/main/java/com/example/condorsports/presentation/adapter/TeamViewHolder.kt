package com.example.condorsports.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.condorsports.data.Team
import com.example.condorsports.databinding.ItemTeamBinding
import com.squareup.picasso.Picasso

class TeamViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemTeamBinding.bind(view)

    fun bind(team: Team){
        binding.tvName.text = team.strTeam
        binding.tvStadium.text = team.strStadium
        Picasso.get().load(team.strTeamBadge).into(binding.ivBadge)
    }
}