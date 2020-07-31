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

    private var fragmentCallback: FragmentCallback? = null

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentCallback)
            fragmentCallback = context
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
            override fun onClickDelete(data: ListObject) {
                deleteMemo(data)
            }

            override fun onClickDone(data: ListObject) {
                doneMemo(data)
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
            println("間違い探し")
        }
        AlertDialog.Builder(requireContext())
            .setTitle("やることを決める")
            .setMessage("はやく")
            .setView(editText)
            .setPositiveButton("頑張れ") { _, _ ->
                println("間違い探し4")
                println("$editText")
                saveMemo(editText.text.toString())
            }
            .show()
        println("間違い探し2")

    }

    private fun saveMemo(memoTitle: String) {
        println("間違い探し5")
        println("${memoTitle}2")
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                realm.insertOrUpdate(ListObject().apply {
                    edit_text = memoTitle
                    done_edit_text = memoTitle
                })
            }
        }
        initData()
        println("間違い探し3")
        println("${memoTitle}")
    }


    private fun deleteMemo(data: ListObject) {
        ListObject.delete(data)
        initData()
    }

    private fun doneMemo(data: ListObject) {
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                realm.insertOrUpdate(data.apply {
                    hasCompleted = true
                })
            }
        }
        fragmentCallback?.onCompleted()
        initData()
    }

    private fun initData() {
        val list = ListObject.findToDoAll()
        toDoAdapter.refresh(list)
        // toDoAdapter.refresh(ListObject.findToDoAll())
    }


}
