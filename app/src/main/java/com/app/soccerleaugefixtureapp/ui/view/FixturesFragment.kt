package com.app.soccerleaugefixtureapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.soccerleaugefixtureapp.R
import com.app.soccerleaugefixtureapp.data.model.Fixture
import com.app.soccerleaugefixtureapp.ui.adapter.FixtureListAdapter
import com.app.soccerleaugefixtureapp.ui.viewmodel.MainViewModel
import com.app.soccerleaugefixtureapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fixtures.*

import javax.inject.Inject


@AndroidEntryPoint
class FixturesFragment : Fragment() {
    @Inject
    lateinit var mainViewModel: MainViewModel
    private val fixturesAdapter=FixtureListAdapter(arrayListOf())
    companion object {
        const val ARG_POSITION = "position"

        fun getInstance(position: Int): Fragment {
            val doppelgangerFragment = FixturesFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            doppelgangerFragment.arguments = bundle

            return doppelgangerFragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fixtures, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = requireArguments().getInt(ARG_POSITION)
        mainViewModel.getFixtureList(requireContext())
        observeLiveData(position)
        fixList.text= "${position+1}. Hafta"
        fixture_list.layoutManager=LinearLayoutManager(context)
        fixture_list.adapter=fixturesAdapter
    }
    fun observeLiveData(position:Int) {
        mainViewModel.getfixtureList.observe(viewLifecycleOwner, Observer { response ->
            when (response.status) {
                Resource.Status.SUCCESS -> {
                    val groupList=response.data?.filter{it.matchWeek==position+1}
                    fixturesAdapter.UpdateList(groupList!!)

                }
            }


        })
    }


}