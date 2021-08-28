package com.valentinc.whistscoreboard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.valentinc.whistscoreboard.R

class BetDialogAdapter(var context: Context) : RecyclerView.Adapter<BetDialogAdapter.ViewHolder>() {

    var dataList = emptyList<Int>()
    var onItemClick: ((Int) -> Unit)? = null

    internal fun setDataList(dataList: List<Int>) {
        this.dataList = dataList
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bet: Button

        init {
            bet = itemView.findViewById(R.id.bet)
            bet.setOnClickListener {
                onItemClick?.invoke(dataList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_bet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = dataList[position]

        holder.bet.text = data.toString()
    }

    override fun getItemCount() = dataList.size
}