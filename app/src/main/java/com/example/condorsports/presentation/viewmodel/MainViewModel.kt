package com.example.condorsports.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condorsports.data.model.Team
import com.example.condorsports.domain.GetTeamsUseCase
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val mutableTeams = MutableLiveData<List<Team>>()
    val teams: LiveData<List<Team>> get() = mutableTeams

    private val mutableSelectedTeam = MutableLiveData<Team>()
    val selectedTeam: LiveData<Team> get() = mutableSelectedTeam

    var getTeamsUseCase = GetTeamsUseCase()

    fun onCreate(league: String) {
        viewModelScope.launch {
            val result = getTeamsUseCase(league)
            if(!result.isNullOrEmpty()){
                mutableTeams.value = result
            }
        }
    }

    fun selectItem(team: Team) {
        mutableSelectedTeam.value = team
    }
    
}