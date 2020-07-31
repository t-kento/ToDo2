package com.example.todo2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.realm.ListObject
import kotlinx.android.synthetic.main.completed_item_list.view.*
import kotlinx.android.synthetic.main.item_list.view.*

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
        CompletedToDoAdapter.ItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.completed_item_list, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CompletedToDoAdapter.ItemViewHolder)
            onBindViewHolder(holder, position)
    }


    override fun getItemCount(): Int = items2.size

    fun onBindViewHolder(holder: CompletedToDoAdapter.ItemViewHolder, position: Int) {
        val data = items2[position]
        holder.apply {
            done_edit_text.text = data.done_edit_text

        }
    }
    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val done_edit_text = view.done_edit_text
    }

    interface CompletedToDoAdapterCallback {
        fun onClick(data: ListObject)
    }


}