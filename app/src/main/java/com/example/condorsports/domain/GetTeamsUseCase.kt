package com.example.condorsports.domain

import com.example.condorsports.data.TeamRepository
import com.example.condorsports.data.model.Team

class GetTeamsUseCase {

    private val repository = TeamRepository()

    suspend operator fun invoke(league: String): List<Team> = repository.getAllTeams(league)

}