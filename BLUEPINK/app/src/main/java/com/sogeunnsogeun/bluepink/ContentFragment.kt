package com.sogeunnsogeun.bluepink

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import kotlinx.android.synthetic.main.fragment_content.*

/**
 * A simple [Fragment] subclass.
 */
class ContentFragment : AppCompatDialogFragment() {

    lateinit var myView:View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        myView = activity!!.layoutInflater.inflate(
            R.layout.fragment_content,
            LinearLayout(activity),
            false
        )

        //build dialog
        var builder: AlertDialog.Builder = AlertDialog.Builder(activity!!)
        builder.setView(myView)
        return builder.create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view_log.setOnClickListener {
            msgLogListFragment().show(fragmentManager!!, "msgLogListFragment")
            this.dismiss()

        }

        mind_test.setOnClickListener {
            (context as MainActivity).chatWithBot("심리검사")
            this.dismiss()
        }

        update_user.setOnClickListener {
            UserInfoPageFragment().show(fragmentManager!!, "UserInfoPageFragment")
            this.dismiss()
        }

        update_bot.setOnClickListener {
            ChatbotOptionFragment().show(fragmentManager!!, "ChatbotOptionFragment")
            this.dismiss()
        }
        copyright.setOnClickListener {
            InfoSourceFragment().show(fragmentManager!!, "InfosourceFragment")
            this.dismiss()
        }
        developer_info.setOnClickListener {
            DeveloperInfoFragment().show(fragmentManager!!, "DeveloperInfoFragment")
            this.dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return myView
    }


}
