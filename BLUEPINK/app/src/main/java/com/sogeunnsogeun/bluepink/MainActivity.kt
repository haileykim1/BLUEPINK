package com.sogeunnsogeun.bluepink

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.getSystemService
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    private var msgDB: MessageItemDB? = null
    private var msgList = listOf<MessageItem>()
    var btnCnt = 0
    var arr = mutableListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        msgDB = MessageItemDB.getDBInstance(this)

        //Toast.makeText(this, temp, Toast.LENGTH_SHORT).show()
    }

    private fun init() {

        botWelcome()

        dialog_window.setOnClickListener {
            if(UserInfo.get(UserInfo.BOTNUM) == "cat1"){
                chatbot_image.setImageResource(R.drawable.cat1_normal)
            }
        }

        msgsend_btn.setOnClickListener {

            //Close softKeyboard
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow((this.currentFocus as View).windowToken, 0)

            if(arr.isNotEmpty()){
                arr.forEach{
                    dialog_window.removeView(it)
                }
                arr.clear()
            }

            if(!edit_msg.text.isEmpty())
                msgProcess(edit_msg.text.toString())



        }

        content_btn.setOnClickListener {
            ContentFragment().show(supportFragmentManager, "contentfragment")
        }

        when(UserInfo.get(UserInfo.BOTNUM)){
            "cat1" -> chatbot_image.setImageResource(R.drawable.cat1_normal)
            "cat2" -> chatbot_image.setImageResource(R.drawable.cat2)
            "dog1" -> chatbot_image.setImageResource(R.drawable.dog1)
        }

    }


    private fun msgProcess(msg:String){
        sent_msg.visibility = View.VISIBLE
        if(UserInfo.get(UserInfo.BOTNUM) == "cat1")
            chatbot_image.setImageResource(R.drawable.cat1_talking)
        sent_msg.text = "\" $msg \""
        sent_msg.postDelayed(Runnable(){
            sent_msg.visibility = View.INVISIBLE
            if(UserInfo.get(UserInfo.BOTNUM) == "cat1")
                chatbot_image.setImageResource(R.drawable.cat1_normal)
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

        //단비 API POST method로 사용
        val str:String = "{\"input_sentence\" : \"" + msg + "\"}"
        chatWithBot(str)

    }

    fun chatWithBot(message:String){
        val url: String = "https://danbee.ai/chatflow/chatbot/v2.0/7bce08fc-8747-4077-9c72-2d714d3c0e3a/message.do"
        Thread({
            URL(url)
                .openConnection()
                .let{
                    it as HttpURLConnection
                }.apply{
                    setRequestProperty("Content-Type", "application/json;charset=UTF-8")
                    requestMethod = "POST"
                    doOutput = true
                    val outputWriter = OutputStreamWriter(outputStream)
                    outputWriter.write(message)
                    outputWriter.flush()

                }.let {
                    if (it.responseCode == 200) it.inputStream else it.errorStream
                }.let { streamToRead ->
                    BufferedReader(InputStreamReader(streamToRead)).use {
                        val response = StringBuffer()

                        var inputLine = it.readLine()
                        while (inputLine != null) {
                            response.append(inputLine)
                            inputLine = it.readLine()
                        }
                        it.close()
                        var temp = response.toString()
                        runOnUiThread{
                            var result1 = JSONObject(JSONObject(temp).getJSONObject("responseSet").getJSONObject("result").getJSONArray("result").get(0).toString())
                            var resultMsg = result1.get("message").toString()
                            dialog_TV.text = "\" $resultMsg \""
                            val optionList = JSONArray(result1.getJSONArray("optionList").toString())
                            btnCnt = optionList.length()
                            for (i in 0..optionList.length() - 1){
                                var newBtn: Button = Button(this)
                                newBtn.layoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                                newBtn.text = JSONObject(optionList[i].toString())["label"].toString()
                                newBtn.setId(i)

                                dialog_window.addView(newBtn)
                                arr.add(newBtn)
                            }


                            //DB 추가
                            val addRunnable = Runnable{
                                val newMsg = MessageItem()
                                newMsg.isChatbots = true
                                newMsg.msgContent = resultMsg.toString()
                                newMsg.sentTime = System.currentTimeMillis()
                                msgDB?.messageItemDao()?.insert(newMsg)
                                msgDB?.messageItemDao()?.deleteAll()
                            }

                            val addThread = Thread(addRunnable)
                            addThread.start()



                        }
                    }
                }
        }).start()



    }

    fun botWelcome(){
       /* val values = mapOf("user_id" to UserInfo.get(UserInfo.NAME))

        val apiService = Res*/


    }

    public fun changeBot(num:Int){

        when(num){
            0 -> chatbot_image.setImageResource(R.drawable.cat1_normal)
            1 -> chatbot_image.setImageResource(R.drawable.cat2)
            2 -> chatbot_image.setImageResource(R.drawable.dog1)
        }

    }

}
