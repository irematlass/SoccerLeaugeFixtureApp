package com.app.soccerleaugefixtureapp.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.soccerleaugefixtureapp.data.model.Fixture
import com.app.soccerleaugefixtureapp.ui.view.FixturesFragment
import java.io.Serializable


class FixtureAdapter(fragment:Fragment,private var fixtureCount:Int) :FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
       return fixtureCount
    }

    override fun createFragment(position: Int): Fragment {
        return FixturesFragment.getInstance(position)
    }


}