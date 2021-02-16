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
            Toast.makeText(context, "대화로그", Toast.LENGTH_SHORT).show()
            println("대화로그")
        }

        mind_test.setOnClickListener {
            Toast.makeText(context, "심리검사", Toast.LENGTH_SHORT).show()
            println("심리검사")
        }

        update_user.setOnClickListener {
            Toast.makeText(context, "사용자 설정", Toast.LENGTH_SHORT).show()
            println("사용자 설정")
        }

        update_bot.setOnClickListener {
            Toast.makeText(context, "챗봇 변경", Toast.LENGTH_SHORT).show()
            println("챗봇 변경")
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
