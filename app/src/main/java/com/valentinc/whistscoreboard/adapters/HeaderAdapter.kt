package com.valentinc.whistscoreboard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.valentinc.whistscoreboard.R
import com.valentinc.whistscoreboard.models.User

class HeaderAdapter(var context: Context, var isFromSavedList: Boolean = false) : RecyclerView.Adapter<HeaderAdapter.ViewHolder>() {

    var dataList = emptyList<User>()
    var onItemClick: ((User) -> Unit)? = null

    internal fun setDataList(dataList: List<User>) {
        this.dataList = dataList
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var score: TextView
        var place: ImageView

        init {
            name = itemView.findViewById(R.id.name)
            score = itemView.findViewById(R.id.score)
            place = itemView.findViewById(R.id.place)

            if(isFromSavedList) {
                itemView.setOnClickListener {
                    onItemClick?.invoke(dataList[adapterPosition])
                }
            }
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

        if (isStarted()) {
            var place = getPlace(position)

            when (place) {
                1 -> holder.place.setImageDrawable(context.getDrawable(R.drawable.icon_first_place))
                2 -> holder.place.setImageDrawable(context.getDrawable(R.drawable.icon_second_place))
                3 -> holder.place.setImageDrawable(context.getDrawable(R.drawable.icon_third_place))
            }
        }
    }

    private fun getPlace(position: Int): Int{
        var place: Int = 1;
        for(user in dataList){
            if(user.score > dataList[position].score)
                place ++
        }
        return place
    }

    private fun isStarted(): Boolean{
        var isStarted: Boolean = false
        for(user in dataList){
            if(user.score != 0)
                isStarted = true
        }
        return isStarted
    }

    override fun getItemCount() = dataList.size
}