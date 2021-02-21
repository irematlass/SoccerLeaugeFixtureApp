package com.app.soccerleaugefixtureapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.soccerleaugefixtureapp.R
import com.app.soccerleaugefixtureapp.data.model.Team
import com.app.soccerleaugefixtureapp.ui.adapter.TeamsAdapter
import com.app.soccerleaugefixtureapp.ui.viewmodel.MainViewModel
import com.app.soccerleaugefixtureapp.utils.Resource
import com.app.soccerleaugefixtureapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_teams.*
import javax.inject.Inject

@AndroidEntryPoint
class TeamsFragment : Fragment() {

    @Inject
    lateinit var mainViewModel: MainViewModel
    lateinit var currentTeam: List<Team>
    private val teamsAdapter = TeamsAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getTeamList(requireContext())

        teams_list.layoutManager = LinearLayoutManager(context)
        teams_list.adapter = teamsAdapter

        observeLiveData()

        draw_fixture_btn.setOnClickListener {
            var result = Utils.drawFixture(currentTeam as ArrayList<Team>)
            var db = mainViewModel.insertFixture(result)
            val action = TeamsFragmentDirections.actionTeamsFragmentToFixturesViewPagerFragment()
            Navigation.findNavController(view).navigate(action)
        }
        swipeRefreshLayout.setOnRefreshListener {
            teams_list.visibility = View.GONE
            teamListError.visibility = View.GONE
            teamListLoading.visibility = View.VISIBLE
            mainViewModel.getTeamList(requireContext())
            swipeRefreshLayout.isRefreshing = false
        }
    }

    fun observeLiveData() {
        mainViewModel.teamList.observe(viewLifecycleOwner, Observer { response ->
            when (response.status) {
                Resource.Status.SUCCESS -> {
                    teams_list.visibility = View.VISIBLE
                    teamListLoading.visibility = View.GONE
                    currentTeam = response.data!!
                    teamsAdapter.UpdateTeamList(response.data!!)
                }
                Resource.Status.ERROR -> {
                    teamListError.visibility = View.VISIBLE
                    teamListLoading.visibility = View.GONE
                }
                Resource.Status.LOADING -> {
                    teamListLoading.visibility = View.VISIBLE
                    teams_list.visibility = View.GONE
                    teamListError.visibility = View.GONE
                }
            }

        })
    }

}