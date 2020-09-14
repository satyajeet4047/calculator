package com.example.calculator.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R
import com.example.calculator.data.HistoryList
import kotlinx.android.synthetic.main.history_item.view.*

class HistoryListAdapter(private val historyList: HistoryList?) : RecyclerView.Adapter<HistoryListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val operation : TextView = itemView.rv_item

        fun bind(historyData: String){
            operation.text = historyData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
     return historyList?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        this.historyList?.get(position)?.let { holder.bind(it) }
    }

}