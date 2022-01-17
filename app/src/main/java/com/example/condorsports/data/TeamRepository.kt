package com.example.condorsports.data

import com.example.condorsports.data.model.Team
import com.example.condorsports.data.network.TeamService

class TeamRepository {

    private val api = TeamService()

    suspend fun getAllTeams(league: String): List<Team> {
        return api.getTeamsByLeague(league)
    }
}