package com.valentinc.whistscoreboard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.valentinc.whistscoreboard.R
import com.valentinc.whistscoreboard.models.RoundScore

class RoundScoreAdapter (var context: Context) : RecyclerView.Adapter<RoundScoreAdapter.ViewHolder>() {

    var dataList = emptyList<RoundScore>()

    internal fun setDataList(dataList: List<RoundScore>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bet: TextView
        var score: TextView

        init {
            bet = itemView.findViewById(R.id.bet)
            score = itemView.findViewById(R.id.score)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_round_score, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = dataList[position]

        holder.bet.text = data.bet.toString()
        holder.score.text = data.score.toString()
    }

    override fun getItemCount() = dataList.size
}