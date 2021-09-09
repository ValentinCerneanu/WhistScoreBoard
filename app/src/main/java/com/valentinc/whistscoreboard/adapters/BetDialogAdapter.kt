package com.valentinc.whistscoreboard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.valentinc.whistscoreboard.R
import com.valentinc.whistscoreboard.models.Bet

class BetDialogAdapter(var context: Context) : RecyclerView.Adapter<BetDialogAdapter.ViewHolder>() {

    var dataList = emptyList<Bet>()
    var onItemClick: ((Int) -> Unit)? = null

    internal fun setDataList(dataList: List<Bet>) {
        this.dataList = dataList
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bet: Button

        init {
            bet = itemView.findViewById(R.id.bet)
            bet.setOnClickListener {
                onItemClick?.invoke(dataList[adapterPosition].bet)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_bet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = dataList[position]

        if(!data.isPossible) {
            holder.bet.visibility = View.GONE
        }

        holder.bet.text = data.bet.toString()
    }

    override fun getItemCount() = dataList.size
}