package com.app.soccerleaugefixtureapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.app.soccerleaugefixtureapp.R
import com.app.soccerleaugefixtureapp.ui.viewmodel.MainViewModel
import com.app.soccerleaugefixtureapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fixtures.*

import javax.inject.Inject

@AndroidEntryPoint
class FixturesFragment : Fragment() {

    @Inject
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        mainViewModel.getFixtureList(requireContext())
        observeLiveData()
    }
    fun observeLiveData(){
        mainViewModel.getfixtureList.observe(viewLifecycleOwner, Observer {response->
            when (response.status) {
                Resource.Status.SUCCESS -> {
                    fixList.text= response.data!!.toString()
                }
            }



        })
    }

}