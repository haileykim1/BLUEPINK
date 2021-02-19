package com.sogeunnsogeun.bluepink

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_msg_log_list.*

/**
 * A simple [Fragment] subclass.
 */
class msgLogListFragment : AppCompatDialogFragment() {

    lateinit var adapter:MsgLogAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_msg_log_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        adapter = MsgLogAdapter((context as MainActivity).forMsgLog!!)
        rcyView?.adapter = adapter

        (context as MainActivity).forMsgLog!!.forEach {
            println(it)
        }
        rcyView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }


}
