package com.example.todo2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.realm.ListObject
import kotlinx.android.synthetic.main.fragment_to_do.*

class ToDoFragment: Fragment() {

    private val toDoAdapter by lazy { ToDoAdapter(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        initLayout()
        initData()
    }

    private fun initLayout() {
        initClick()
        initRecyclerView()
    }

    private fun initClick() {
        // タスクの追加
    }

    private fun initRecyclerView() {
        memoRecyclerView.adapter = toDoAdapter
    }

    private fun initData() {
        toDoAdapter.refresh(ListObject.findToDoAll())
    }
}