package com.app.soccerleaugefixtureapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.app.soccerleaugefixtureapp.R
import com.app.soccerleaugefixtureapp.data.model.Fixture
import com.app.soccerleaugefixtureapp.data.model.Team
import com.app.soccerleaugefixtureapp.ui.adapter.FixtureAdapter
import com.app.soccerleaugefixtureapp.ui.viewmodel.MainViewModel
import com.app.soccerleaugefixtureapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fixtures.*
import javax.inject.Inject

@AndroidEntryPoint
class FixturesViewPagerFragment : Fragment() {
    @Inject
    lateinit var mainViewModel: MainViewModel
    private lateinit var fixtureAdapter: FixtureAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var groupList:Map<Int,List<Fixture>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fixture_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getFixtureList(requireContext())
        observeLiveData()
        viewPager = view.findViewById(R.id.pager)


    }
    fun observeLiveData() {
        mainViewModel.getfixtureList.observe(viewLifecycleOwner, Observer { response ->
            when (response.status) {
                Resource.Status.SUCCESS -> {
                    val fixtures= response.data!!
                     groupList=fixtures.groupBy { it.matchWeek}
                    fixtureAdapter= FixtureAdapter(this,groupList)

                    viewPager.adapter =fixtureAdapter
                }
            }


        })
    }

}