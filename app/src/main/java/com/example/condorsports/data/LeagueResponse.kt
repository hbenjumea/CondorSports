package com.example.condorsports.data

import com.google.gson.annotations.SerializedName

data class LeagueResponse(
    @SerializedName("teams" ) var teams : ArrayList<Team> = arrayListOf()
)
