package com.example.condorsports.data.network

import com.example.condorsports.core.RetrofitHelper.getRetrofit
import com.example.condorsports.data.model.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamService {

    suspend fun getTeamsByLeague(league: String): List<Team>{
        return withContext(Dispatchers.IO){
            val call = getRetrofit().create(TeamApiClient::class.java).getTeamsByLeague("search_all_teams.php?l=$league")
            val leagueResponse = call.body()
            leagueResponse?.teams ?: emptyList()
        }

    }
}