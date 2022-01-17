package com.example.condorsports.presentation.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.condorsports.R
import com.example.condorsports.data.model.Team
import com.example.condorsports.databinding.MainFragmentBinding
import com.example.condorsports.presentation.adapter.TeamAdapter
import com.example.condorsports.presentation.viewmodel.MainViewModel

class MainFragment : Fragment() {

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
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.onCreate("Spanish%20La%20Liga")
        viewModel.teams.observe(requireActivity(),{
            Log.e("hugo", "hjhgfjhgfj")
            if (it.isNotEmpty()) {
                teams.clear()
                teams.addAll(it)
                teamAdapter.notifyDataSetChanged()
            }else{
                showError()
            }
        })
        initRecyclerView()
    }

    private fun initRecyclerView() {
        teamAdapter = TeamAdapter(teams){
            onItemSelected(it)
        }
        binding.rvTeams.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTeams.adapter = teamAdapter
    }

    private fun onItemSelected(team: Team){
        //Toast.makeText(requireContext(),"Team: ${team.strTeam}", Toast.LENGTH_SHORT).show()
        viewModel.selectItem(team)
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
    }

    private fun showError() {
        Toast.makeText(requireContext(),"An error has occurred",Toast.LENGTH_SHORT).show()
    }

}