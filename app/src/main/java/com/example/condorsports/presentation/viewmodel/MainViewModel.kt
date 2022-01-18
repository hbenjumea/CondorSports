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

    val isLoading = MutableLiveData<Boolean>()

    var getTeamsUseCase = GetTeamsUseCase()

    fun onCreate(league: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getTeamsUseCase(league)
            if(!result.isNullOrEmpty()){
                mutableTeams.value = result
                isLoading.postValue(false)
            }
        }
    }

    fun selectItem(team: Team) {
        mutableSelectedTeam.value = team
    }
    
}