package com.valentinc.whistscoreboard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.valentinc.whistscoreboard.R
import com.valentinc.whistscoreboard.models.Answer

class AnswerDialogAdapter(var context: Context) : RecyclerView.Adapter<AnswerDialogAdapter.ViewHolder>() {

    var dataList = emptyList<Answer>()
    var onItemClick: ((Boolean) -> Unit)? = null

    internal fun setDataList(dataList: List<Answer>) {
        this.dataList = dataList
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var answer: Button

        init {
            answer = itemView.findViewById(R.id.answer)
            answer.setOnClickListener {
                onItemClick?.invoke(dataList[adapterPosition].isPositive == true)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_answer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = dataList[position]

        holder.answer.text = data.text
    }

    override fun getItemCount() = dataList.size
}