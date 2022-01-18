package com.example.condorsports.presentation.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.condorsports.R
import com.example.condorsports.data.model.Team
import com.example.condorsports.databinding.MainFragmentBinding
import com.example.condorsports.presentation.adapter.TeamAdapter
import com.example.condorsports.presentation.viewmodel.MainViewModel

/**
 * Fragment for the teams
 */
class MainFragment : Fragment(), AdapterView.OnItemSelectedListener {

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

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.leaguess_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.leaguesSpinner.adapter = adapter
        }
        binding.leaguesSpinner.onItemSelectedListener = this

        //Get the teams
        viewModel.onCreate("Spanish%20La%20Liga")

        //When get the teams then update the list
        viewModel.teams.observe(requireActivity(),{
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

    /**
     * When touch a team then navigate to detail
     */
    private fun onItemSelected(team: Team){
        viewModel.selectItem(team)
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
    }

    private fun showError() {
        Toast.makeText(requireContext(),"An error has occurred",Toast.LENGTH_SHORT).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.selectedItem.toString()
        viewModel.onCreate(item)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}