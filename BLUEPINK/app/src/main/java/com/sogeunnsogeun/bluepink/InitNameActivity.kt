package com.sogeunnsogeun.bluepink

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_init_name.*

class InitNameActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_name)

        initNameSkipBtn.setOnClickListener {
            UserInfo.set(UserInfo.NAME, UserInfo.DEFAULT_NAME)
            UserInfo.set(UserInfo.NAME_PASSED, true)

            nextActivity()
        }

        initNameOkBtn.setOnClickListener {
            if(initNameText.text.isEmpty()){
                Toast.makeText(this, "입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            UserInfo.set(UserInfo.NAME, initNameText.text)
            UserInfo.set(UserInfo.NAME_PASSED, true)

            nextActivity()
        }
    }

    private fun nextActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}