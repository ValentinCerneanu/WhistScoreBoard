package com.valentinc.whistscoreboard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.valentinc.whistscoreboard.R

class RoundNumberAdapter (var context: Context) : RecyclerView.Adapter<RoundNumberAdapter.ViewHolder>() {

    var dataList = emptyList<Int>()

    internal fun setDataList(dataList: List<Int>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var roundNumber: TextView

        init {
            roundNumber = itemView.findViewById(R.id.round_number)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_round_number, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = dataList[position]

        if(data == 0)
            holder.itemView.visibility = View.INVISIBLE
        else
            holder.itemView.visibility = View.VISIBLE

        holder.roundNumber.text = data.toString()
    }

    override fun getItemCount() = dataList.size
}