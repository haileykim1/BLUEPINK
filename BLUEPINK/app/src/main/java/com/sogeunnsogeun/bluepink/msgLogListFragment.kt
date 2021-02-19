package com.sogeunnsogeun.bluepink

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import kotlinx.android.synthetic.main.fragment_msg_log_list.*

/**
 * A simple [Fragment] subclass.
 */
class msgLogListFragment : AppCompatDialogFragment() {

    lateinit var adapter:MsgLogAdapter
    private var msgDB: MessageItemDB? = null

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_msg_log_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        msgDB = MessageItemDB.getDBInstance(context!!)
        val addRunnable = Runnable{
            val temp = msgDB?.messageItemDao()?.getAll()!!
            adapter = MsgLogAdapter(temp)
            temp.forEach{
                println(it)
            }
            rcyView?.adapter = adapter
            rcyView.invalidate()
        }

        val addThread = Thread(addRunnable)
        addThread.start()



    }


}
