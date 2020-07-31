package com.example.todo2

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realm.ListObject
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_completed_to_do.*

class CompletedToDoFragment : Fragment() {

    private val completedToDoAdapter by lazy { CompletedToDoAdapter(requireContext()) }

    private var fragmentCallback: FragmentCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_completed_to_do, container, false)
    }

    fun updateTasks() {
        println("ここまで来ました")

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
        initRecyclerView()

    }

    private fun initRecyclerView() {
        completedToDoAdapter.callback = object : CompletedToDoAdapter.CompletedToDoAdapterCallback {
            override fun onClick(data: ListObject) {}
        }
        completedRecyclerView.apply {
            adapter = completedToDoAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

////    private fun completedMemo() {
////        val completedText = EditText(requireContext())
////
////        AlertDialog.Builder(requireContext())
////            .setView(completedText)
////            .setPositiveButton("未来") { _, _ ->
////                completedSaveMemo(completedText.text.toString())
////            }
////            .show()
//    }


    private fun completedSaveMemo(completedmemoTitle: String) {
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                realm.insertOrUpdate(ListObject().apply {
                    done_edit_text = completedmemoTitle
                })
            }
        }
    }
    private fun initData() {
        val list = ListObject.findToDoAll()
        completedToDoAdapter.refresh(list)
    }

}