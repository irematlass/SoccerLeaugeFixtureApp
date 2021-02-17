package com.app.soccerleaugefixtureapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.soccerleaugefixtureapp.R
import com.app.soccerleaugefixtureapp.data.model.Team
import kotlinx.android.synthetic.main.team_list_item.view.*

class TeamsAdapter(val teamList:ArrayList<Team>):RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {
    class TeamViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.team_list_item, parent, false)
        return TeamViewHolder(view)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.view.team_name_txt.text=teamList[position].name
    }

    fun UpdateTeamList(newTeamList:List<Team>){
        teamList.clear()
        teamList.addAll(newTeamList)
        notifyDataSetChanged()
    }
}