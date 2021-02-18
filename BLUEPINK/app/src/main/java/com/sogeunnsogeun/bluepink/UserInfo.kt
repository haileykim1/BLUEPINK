package com.sogeunnsogeun.bluepink

import org.json.JSONArray
import org.json.JSONObject

object UserInfo {

    private val userInformation:JSONObject = initializeUserInfo()

    const val NAME = "name"
    const val NAME_PASSED = "name_passed"
    const val DEFAULT_NAME = "사용자"

    var sUserName: String = "사용자"

    private fun initializeUserInfo():JSONObject{
        var data:String = FileManager.readFile()

        return if(data.isNotEmpty()){
            JSONObject(data)
        }else{
            JSONObject()
        }
    }

    fun get(key:String):Any?{
        if(userInformation.has(key))
            return userInformation[key]

        return null
    }

    private fun getString(key: String):String{
        if(userInformation.has(key))
            return userInformation[key].toString()

        return ""
    }

    fun setUserName(newName:String){
        sUserName = newName
    }

    private fun getInt(key:String):Int {
        if (userInformation.has(key)) {
            return userInformation[key].toString().toInt()
        }

        return 0
    }

    private fun getJsonArray(key:String): JSONArray {
        var default: JSONArray = JSONArray()

        if (userInformation.has(key)) {
            return userInformation[key] as JSONArray
        }

        if (key == "dayArray")
            default = defaultDayArray()

        return default
    }

    private fun defaultDayArray(): JSONArray {
        return JSONArray(arrayListOf<Int>(0,0,0,0,0,0,0))
    }

    fun updateVar(key: String, value: Any) {
        if (key == NAME) {
            sUserName = value.toString()
        }
    }

    fun set(key: String, value:Any) {
        var map: HashMap<String, Any> = HashMap<String, Any>()

        if (key != null) {
            map[key] = value
        }

        set(map)
    }

    fun set(map: HashMap<String, Any>) {
        for ((key, value) in map) {
            userInformation.put(key, value)
            updateVar(key, value)
        }

        FileManager.saveFile(userInformation)
    }

    fun has(key: String):Boolean {
        return userInformation.has(key)
    }
}