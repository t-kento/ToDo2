package com.example.todo2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.realm.ListObject
import kotlinx.android.synthetic.main.item_list.view.*

class ToDoAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<ListObject>()

    var callback: ToDoAdapterCallback? = null


    fun refresh(list: List<ListObject>) {
        items.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        )
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder)
            onBindViewHolder(holder,position)
    }

    private fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = items[position]
        holder.apply {
            edit_text.text = data.edit_text
            deleteButton.setOnClickListener{
                callback?.onClickDelete(data)
            }
            doneButton.setOnClickListener{
                callback?.onClickDone(data)
            }
        }
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val edit_text = view.edit_text
        val deleteButton = view.deleteButton
        val doneButton=view.doneButton
    }
    interface ToDoAdapterCallback{
        fun onClickDelete(data: ListObject)
        fun onClickDone(data: ListObject)
    }


}