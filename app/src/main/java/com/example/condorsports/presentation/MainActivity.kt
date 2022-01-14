package com.example.condorsports.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.condorsports.R
import com.example.condorsports.data.APIService
import com.example.condorsports.data.Team
import com.example.condorsports.databinding.ActivityMainBinding
import com.example.condorsports.presentation.adapter.TeamAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var teamAdapter: TeamAdapter
    private val teams = mutableListOf<Team>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        getTeamsByLeague("Spanish%20La%20Liga")
    }

    private fun initRecyclerView() {
        teamAdapter = TeamAdapter(teams)
        binding.rvTeams.layoutManager = LinearLayoutManager(this)
        binding.rvTeams.adapter = teamAdapter
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getTeamsByLeague(league: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getTeamsByLeague("search_all_teams.php?l=$league")
            val leagueResponse = call.body()
            runOnUiThread {
                if (call.isSuccessful){
                    val teamsFromResponse = leagueResponse?.teams ?: emptyList()
                    teams.clear()
                    teams.addAll(teamsFromResponse)
                    teamAdapter.notifyDataSetChanged()
                }else{
                    showError()
                }
            }

        }
    }

    private fun showError() {
        Toast.makeText(this,"An error has occurred",Toast.LENGTH_SHORT).show()
    }
}