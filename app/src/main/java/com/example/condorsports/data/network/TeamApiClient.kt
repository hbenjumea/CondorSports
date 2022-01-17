package com.example.condorsports.data.network

import com.example.condorsports.data.model.LeagueResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface TeamApiClient {

    @GET
    suspend fun getTeamsByLeague(@Url url: String): Response<LeagueResponse>
}