package com.sogeunnsogeun.bluepink

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MessageItem::class], version = 1)
abstract class MessageItemDB: RoomDatabase(){
    abstract fun messageItemDao(): MessageItemDao

    companion object{
        private var dbInstance: MessageItemDB? = null

        fun getDBInstance(context: Context): MessageItemDB? {
            if(dbInstance == null){
                synchronized(MessageItemDB::class){
                    dbInstance = Room.databaseBuilder(context.applicationContext,
                    MessageItemDB::class.java, "messageitem.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstance
        }

        fun destroyInstance(){
            dbInstance = null
        }

    }

}