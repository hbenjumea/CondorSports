package com.example.condorsports.data.network

import com.example.condorsports.core.RetrofitHelper.getRetrofit
import com.example.condorsports.data.model.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Service to get teams by league from API
 */
class TeamService {

    /**
     * Function to get teams by league from API
     * @param league
     * @return list of teams
     */
    suspend fun getTeamsByLeague(league: String): List<Team>{
        return withContext(Dispatchers.IO){
            val call = getRetrofit().create(TeamApiClient::class.java).getTeamsByLeague("search_all_teams.php?l=$league")
            val leagueResponse = call.body()
            leagueResponse?.teams ?: emptyList()
        }
    }
}