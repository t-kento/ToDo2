package com.example.todo2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, ToDoActivity::class.java))
    }

    companion object {
        fun start(activity: Activity) =
            activity.startActivity(Intent(activity, ToDoActivity::class.java))
    }


}
