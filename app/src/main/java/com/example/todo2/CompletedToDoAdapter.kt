package com.example.todo2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.realm.ListObject

class CompletedToDoAdapter (val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items2 = mutableListOf<ListObject>()

    var callback: CompletedToDoAdapterCallback? = null

    fun refresh(list: List<ListObject>) {
        items2.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ToDoAdapter.ItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.fragment_completed_to_do, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ToDoAdapter.ItemViewHolder)
            onBindViewHolder(holder, position)
    }


    override fun getItemCount(): Int = items2.size

    fun onBindViewHolder(holder: ToDoAdapter.ItemViewHolder, position: Int) {
        val data = items2[position]
        holder.apply {

        }
    }

    interface CompletedToDoAdapterCallback {
        fun onClick(data: ListObject)
    }

}