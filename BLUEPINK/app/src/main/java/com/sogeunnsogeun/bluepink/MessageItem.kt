package com.sogeunnsogeun.bluepink

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "messageitem")
data class MessageItem(@PrimaryKey var id:Long?,
                       @ColumnInfo(name = "ischatbot")var isChatbots: Boolean,
                       @ColumnInfo(name = "msgcontent")var msgContent: String,
                       @ColumnInfo(name = "senttime")var sentTime: Long) {

    constructor():this(null, false, "", 0)

    fun getSender(): String{
        if(isChatbots)  return "챗봇"

        return "사용자"
    }



}