package com.example.condorsports.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class to map the response from API
 */
data class LeagueResponse(
    @SerializedName("teams" ) var teams : ArrayList<Team> = arrayListOf()
)
