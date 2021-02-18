package com.sogeunnsogeun.bluepink

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_chatbot_option.*

/**
 * A simple [Fragment] subclass.
 */
class ChatbotOptionFragment : AppCompatDialogFragment() {

    lateinit var myView:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return myView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        myView = activity!!.layoutInflater.inflate(R.layout.fragment_chatbot_option, LinearLayout(activity), false)

        return AlertDialog.Builder(activity!!)
            .setView(myView)
            .setNegativeButton("취소", { dialog, which -> dialog.dismiss()})
            .setPositiveButton("적용", { dialog, which ->
                //챗봇 변경
                if(bot_cat1.isChecked == true){
                    UserInfo.set(UserInfo.BOTNUM, "cat1")
                    (context as MainActivity).changeBot(0)
                }else if(bot_cat2.isChecked == true){
                    UserInfo.set(UserInfo.BOTNUM, "cat2")
                    (context as MainActivity).changeBot(1)
                }else if(bot_dog.isChecked == true){
                    UserInfo.set(UserInfo.BOTNUM, "dog1")
                    (context as MainActivity).changeBot(2)
                }else{
                    UserInfo.set(UserInfo.BOTNUM, "cat1")
                    (context as MainActivity).changeBot(0)
                }
            })
            .create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        when(UserInfo.get(UserInfo.BOTNUM)){
            "cat1" -> bot_cat1.isChecked = true
            "cat2" -> bot_cat2.isChecked = true
            "dog1" -> bot_dog.isChecked = true
            else -> bot_cat1.isChecked = true
        }

    }
}
