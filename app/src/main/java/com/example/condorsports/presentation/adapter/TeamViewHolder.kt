package com.example.condorsports.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.condorsports.data.model.Team
import com.example.condorsports.databinding.ItemTeamBinding
import com.squareup.picasso.Picasso

class TeamViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemTeamBinding.bind(view)

    fun bind(team: Team, onClickListener:(Team) -> Unit){
        binding.tvName.text = team.strTeam
        binding.tvStadium.text = team.strStadium
        Picasso.get().load(team.strTeamBadge).into(binding.ivBadge)

        itemView.setOnClickListener {onClickListener(team)}
    }
}