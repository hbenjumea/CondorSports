package com.example.condorsports.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
    suspend fun getTeamsByLeague(@Url url: String): Response<LeagueResponse>
}