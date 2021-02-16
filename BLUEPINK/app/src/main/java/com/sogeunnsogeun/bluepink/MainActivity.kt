package com.sogeunnsogeun.bluepink

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    private var msgDB: MessageItemDB? = null
    private var msgList = listOf<MessageItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        msgDB = MessageItemDB.getDBInstance(this)

    }

    private fun init() {



        dialog_window.setOnClickListener {
            //Toast.makeText(this, "다음 메시지", Toast.LENGTH_SHORT).show()
        }

        msgsend_btn.setOnClickListener {

            //Close softKeyboard
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow((this.currentFocus as View).windowToken, 0)

            if(!edit_msg.text.isEmpty())
                msgProcess()

        }

        content_btn.setOnClickListener {
            ContentFragment().show(supportFragmentManager, "contentfragment")
        }

    }


    private fun msgProcess(){
        val msg = edit_msg.text
        sent_msg.visibility = View.VISIBLE
        sent_msg.text = msg
        sent_msg.postDelayed(Runnable(){
            sent_msg.visibility = View.INVISIBLE
        }, 3000)


        //Save MessageItem to DB
        val addRunnable = Runnable{
            val newMsg = MessageItem()
            newMsg.isChatbots = false
            newMsg.msgContent = msg.toString()
            newMsg.sentTime = System.currentTimeMillis()
            msgDB?.messageItemDao()?.insert(newMsg)
        }

        val addThread = Thread(addRunnable)
        addThread.start()
        println("$msg 추가완료")

        edit_msg.text = null



    }



}
