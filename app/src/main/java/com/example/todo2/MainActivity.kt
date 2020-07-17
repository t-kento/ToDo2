package com.example.todo2

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realm.ListObject
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val customAdapter by lazy { CustomAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        initData()

        val childFragment= ChildFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container,childFragment)
        transaction.commit()
    }

    private fun initialize() {
        initLayout()
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
        customAdapter.callback = object : CustomAdapter.CustomAdapterCallback {
            override fun onClick(data: ListObject) {
                deleteMemo(data)
            }
        }
        memoRecyclerView.apply {
            adapter = customAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun addMemo() {
        val editText = EditText(this).apply {
            hint = "やることを入力"
        }
        AlertDialog.Builder(this)
            .setMessage("ここに入力")
            .setView(editText)
            .setPositiveButton("すぐやれよ") { _, _ ->
                saveMemo(editText.text.toString())
            }
            .show()
    }


    private fun saveMemo(memoTitle: String) {
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                realm.insertOrUpdate(ListObject().apply {
                    title = memoTitle
                })
            }
        }
        initData()
    }
   private fun send{
       ChildFragment().apply {
           aragumets = BUndle().apply{
               putString(ChildFragment.title)
           }
       }

   }

    private fun deleteMemo(data: ListObject) {
        ListObject.delete(data)
        initData()
    }

    private fun initData() {
        val list = ListObject.findAll()
        customAdapter.refresh(list)
    }
}

