package com.valentinc.whistscoreboard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.valentinc.whistscoreboard.R
import com.valentinc.whistscoreboard.models.User

class HeaderAdapter(var context: Context) : RecyclerView.Adapter<HeaderAdapter.ViewHolder>() {

    var dataList = emptyList<User>()

    internal fun setDataList(dataList: List<User>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var score: TextView

        init {
            name = itemView.findViewById(R.id.name)
            score = itemView.findViewById(R.id.score)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_header, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = dataList[position]

        holder.name.text = data.name
        holder.score.text = data.score.toString()
    }

    override fun getItemCount() = dataList.size
}