package com.example.condorsports.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.condorsports.data.APIService
import com.example.condorsports.data.Team
import com.example.condorsports.databinding.MainFragmentBinding
import com.example.condorsports.presentation.adapter.TeamAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var teamAdapter: TeamAdapter
    private val teams = mutableListOf<Team>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getTeamsByLeague("Spanish%20La%20Liga")
    }

    private fun initRecyclerView() {
        teamAdapter = TeamAdapter(teams){
            onItemSelected(it)
        }
        binding.rvTeams.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTeams.adapter = teamAdapter
    }

    private fun onItemSelected(team: Team){
        Toast.makeText(requireContext(),"Team: ${team.strTeam}", Toast.LENGTH_SHORT).show()
    }

    private fun getTeamsByLeague(league: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getTeamsByLeague("search_all_teams.php?l=$league")
            val leagueResponse = call.body()
            activity?.runOnUiThread {
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
        Toast.makeText(requireContext(),"An error has occurred",Toast.LENGTH_SHORT).show()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}