package com.example.condorsports.presentation.view

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.condorsports.data.model.Event
import com.example.condorsports.data.model.Team
import com.example.condorsports.databinding.DetailFragmentBinding
import com.example.condorsports.presentation.adapter.EventAdapter
import com.example.condorsports.presentation.adapter.LinkAdapter
import com.example.condorsports.presentation.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import java.lang.Exception

class DetailFragment : Fragment() {

    private lateinit var binding: DetailFragmentBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var eventAdapter: EventAdapter
    private val events = mutableListOf<Event>()

    private lateinit var linkAdapter: LinkAdapter
    private val links = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        val team = viewModel.selectedTeam.value

        if (team != null) {
            binding.tvTeamName.text = team.strTeam
            binding.tvTeamDescription.text = team.strDescriptionEN
            binding.tvTeamYear.text = team.intFormedYear
            Picasso.get().load(team.strTeamBadge).into(binding.ivBadgeDetail)
            Picasso.get().load(team.strTeamJersey).into(binding.ivJersey)

            initEventRecyclerView()
            loadEventRecyclerView(team)

            initLinkRecyclerView()
            loadLinkRecyclerView(team)

        }
    }

    private fun initEventRecyclerView() {
        eventAdapter = EventAdapter(events)
        binding.rvEvents.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEvents.adapter = eventAdapter
    }

    private fun loadEventRecyclerView(team: Team) {
        events.clear()
        if(!team.strLeague2.isNullOrEmpty()){
            val event = Event(team.idLeague2,team.strLeague2)
            events.add(event)
        }
        if(!team.strLeague3.isNullOrEmpty()){
            val event = Event(team.idLeague3,team.strLeague3)
            events.add(event)
        }
        if(!team.strLeague4.isNullOrEmpty()){
            val event = Event(team.idLeague4,team.strLeague4)
            events.add(event)
        }
        if(!team.strLeague5.isNullOrEmpty()){
            val event = Event(team.idLeague5,team.strLeague5)
            events.add(event)
        }
        if(!team.strLeague6.isNullOrEmpty()){
            val event = Event(team.idLeague6,team.strLeague6)
            events.add(event)
        }

        eventAdapter.notifyDataSetChanged()
    }

    private fun initLinkRecyclerView() {
        linkAdapter = LinkAdapter(links){
            onItemSelected(it)
        }
        binding.rvLinks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLinks.adapter = linkAdapter
    }

    /**
     * strWebsite
    facebook
    twitter
    instagram
    youtube
     */
    private fun loadLinkRecyclerView(team: Team) {
        links.clear()

        if(!team.strFacebook.isNullOrEmpty()){
            links.add(team.strFacebook!!)
        }
        if(!team.strInstagram.isNullOrEmpty()){
            links.add(team.strInstagram!!)
        }
        if(!team.strTwitter.isNullOrEmpty()){
            links.add(team.strTwitter!!)
        }
        if(!team.strWebsite.isNullOrEmpty()){
            links.add(team.strWebsite!!)
        }
        if(!team.strYoutube.isNullOrEmpty()){
            links.add(team.strYoutube!!)
        }

        linkAdapter.notifyDataSetChanged()
    }

    private fun onItemSelected(link: String){
        //Open url
        try {
            val uri = Uri.parse("https://$link")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }catch (ex: Exception){
            Toast.makeText(requireContext(), "Failed to parse URL or can't open URL.", Toast.LENGTH_LONG).show()
        }

    }

}