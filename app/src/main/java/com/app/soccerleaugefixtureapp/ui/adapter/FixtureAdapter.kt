package com.app.soccerleaugefixtureapp.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.soccerleaugefixtureapp.data.model.Fixture
import com.app.soccerleaugefixtureapp.ui.view.FixturesFragment
import java.io.Serializable


class FixtureAdapter(fragment:Fragment,private val fixtures:Map<Int,List<Fixture>>) :FragmentStateAdapter(fragment) {
        private  val ARG_OBJECT = "object"
    override fun getItemCount(): Int {
       return fixtures.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FixturesFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
        putSerializable(ARG_OBJECT,fixtures[position] as Serializable)


        }
        return fragment
    }


}