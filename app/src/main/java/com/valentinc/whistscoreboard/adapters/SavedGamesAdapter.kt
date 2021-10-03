package com.valentinc.whistscoreboard.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.valentinc.whistscoreboard.R
import com.valentinc.whistscoreboard.models.GameWrapper

class SavedGamesAdapter(var context: Context) : RecyclerView.Adapter<SavedGamesAdapter.ViewHolder>() {

    var gamesList = emptyList<GameWrapper>()

    internal fun setDataList(dataList: List<GameWrapper>) {
        this.gamesList = dataList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var childRecyclerView: RecyclerView

        init {
            childRecyclerView = itemView.findViewById(R.id.saved_game)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.saved_game_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gamesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem: GameWrapper = gamesList[position]

        holder.childRecyclerView.layoutManager = GridLayoutManager(context, currentItem.users.size)
        holder.childRecyclerView.setHasFixedSize(true)

        val headerAdapter = HeaderAdapter(holder.childRecyclerView.context)
        headerAdapter.setDataList(currentItem.users)
        holder.childRecyclerView.adapter = headerAdapter
    }

}