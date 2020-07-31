package com.example.todo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,FragmentCallback{

    private val viewPagerAdapter by lazy { TodoViewPagerAdapter(this) }

    private var index : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
//        var result = 1 + index!!
    }

    private fun initialize() {
        initLayout()
    }

    private fun initLayout() {
        initViewPager2()
        initTabLayout()
    }

    private fun initViewPager2() {
        viewPager2.apply {
            adapter = viewPagerAdapter
        }
    }

    private fun initTabLayout() {
        TabLayoutMediator(tabLayout, viewPager2) { tab: TabLayout.Tab, position: Int ->
            tab.text = viewPagerAdapter.items[position].title
        }.attach()
    }

    class TodoViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        val items: List<Item> =
            listOf(Item(ToDoFragment(), "未完了タスク"), Item(CompletedToDoFragment(), "完了タスク"))

        override fun getItemCount(): Int = items.size

        override fun createFragment(position: Int): Fragment = items[position].fragment

        class Item(val fragment: Fragment, val title: String)
    }

    override fun onCompleted() {
        println("押されました")
        println("${title}です")
       viewPagerAdapter.items.firstOrNull{it.fragment is CompletedToDoFragment}?.also {
           if (it.fragment is CompletedToDoFragment)
               it.fragment.updateTasks()
       }
    }

    override fun onCanceled() {
    }
}

