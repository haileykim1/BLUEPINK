package com.sogeunnsogeun.bluepink

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        GlobalContext.setContext(this)

        Handler().postDelayed({
            val intent: Intent = if(UserInfo.has(UserInfo.NAME_PASSED) && UserInfo.get(UserInfo.NAME_PASSED) == true){
                Intent(this, MainActivity::class.java)
            }else{
                Intent(this, InitNameActivity::class.java)
            }

            startActivity(intent)
            finish()
        }, 1500)

    }
}