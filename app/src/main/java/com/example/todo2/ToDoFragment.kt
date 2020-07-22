package com.example.todo2

import android.app.AlertDialog
import android.app.AlertDialog.*
import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realm.ListObject
import io.realm.Realm
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_to_do.*
import com.example.todo2.ToDoAdapter as ToDoAdapter

class ToDoFragment : Fragment() {

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
        fab.setOnClickListener {
            addMemo()
        }
    }

    private fun initRecyclerView() {
        toDoAdapter.callback = object : ToDoAdapter.ToDoAdapterCallback {
            override fun onClick(data: ListObject) {
                deleteMemo(data)
            }
        }
        memoRecyclerView.apply {
            adapter = toDoAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun addMemo() {
        val editText = EditText(requireContext()).apply {
            hint = "やること"
        }
        AlertDialog.Builder(requireContext())
            .setTitle("やることを決める")
            .setMessage("はやくやれよ")
            .setView(editText)
            .setPositiveButton("頑張れ") { _, _ ->
                saveMemo(editText.text.toString())
            }
            .show()
    }

    private fun saveMemo(memoTitle: String) {
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                realm.insertOrUpdate(ListObject().apply {
                    edit_text = memoTitle
                })
            }
        }
        initData()
    }

    private fun deleteMemo(data: ListObject) {
        ListObject.delete(data)
        initData()
    }

    private fun initData() {
        val list=ListObject.findAll()
        toDoAdapter.refresh(list)
       // toDoAdapter.refresh(ListObject.findToDoAll())
    }


}
