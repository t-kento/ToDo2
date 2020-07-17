package com.example.todo2

import android.content.Context
import android.icu.text.CaseMap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import io.realm.internal.OsSharedRealm.initialize
import kotlinx.android.synthetic.main.item_list.*

class ChildFragment : BaseFragment() {
    var mContext: Context? =null

    companion object {
        fun createFragment(mc: Context): ChildFragment {
            val childFragment = ChildFragment()
            childFragment.mContext = mc
            return childFragment
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return layoutInflater.inflate(R.layout.item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
       //  intialize()
    }
    private fun intialize(){
        initLayout()
    }

    private fun initLayout(){
        initText()
    }

    private fun initText(){
   //     textView1.text =arguments?.getString(title)
    }

