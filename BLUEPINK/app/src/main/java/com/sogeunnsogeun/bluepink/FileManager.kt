package com.sogeunnsogeun.bluepink

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.*

object FileManager {
    const val USER_INFO: String = "userInfo"

    private val c: Context = GlobalContext.getContext()

    private fun getPath(path:String): File? {
        val file: File? = c.getFileStreamPath(path)

        if(file == null || !file.exists())
            return null

        return file
    }

    fun reset(){
        saveFile(JSONObject())
    }

    fun saveFile(data: Any, fileName:String = USER_INFO){
        val os = c.openFileOutput(fileName, AppCompatActivity.MODE_PRIVATE)
        val bw = BufferedWriter(OutputStreamWriter(os))
        bw.write(data.toString())
        bw.flush()
    }

    fun readFile(fileName: String = USER_INFO): String{
        var os:FileInputStream
        try{
            os = c.openFileInput(fileName)
        } catch(ex: FileNotFoundException){
            saveFile(JSONObject())

            return ""
        }

        val br = BufferedReader(InputStreamReader(os))
        var sb = StringBuilder()
        var line = br.readLine()
        while(line != null){
            sb.append(line).append("\n")
            line = br.readLine()
        }
        br.close()

        return sb.toString()
    }

}