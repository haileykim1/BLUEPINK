package com.sogeunnsogeun.bluepink

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.nio.charset.CodingErrorAction.REPLACE

//MessageItem Database Access Object
@Dao
interface MessageItemDao{
    @Query("SELECT * FROM messageitem")
    fun getAll():List<MessageItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(msg: MessageItem)

    @Query("DELETE from messageitem")
    fun deleteAll()



}