package com.app.soccerleaugefixtureapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.soccerleaugefixtureapp.R
import com.app.soccerleaugefixtureapp.data.model.Fixture
import com.app.soccerleaugefixtureapp.data.model.Team
import kotlinx.android.synthetic.main.fixture_list_item.view.*


class FixtureListAdapter(val fixtureList: ArrayList<Fixture>) :
    RecyclerView.Adapter<FixtureListAdapter.FixtureHolder>() {
    class FixtureHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.fixture_list_item, parent, false)
        return FixtureHolder(view)
    }

    override fun getItemCount(): Int {
      return fixtureList.size
    }

    override fun onBindViewHolder(holder: FixtureHolder, position: Int) {
        holder.view.homeTeam_txt.text=fixtureList[position].homeTeam
        holder.view.awayTeam_txt.text=fixtureList[position].awayTeam

    }
    fun UpdateList(newList: List<Fixture>) {
        fixtureList.clear()
        fixtureList.addAll(newList)
        notifyDataSetChanged()
    }
}