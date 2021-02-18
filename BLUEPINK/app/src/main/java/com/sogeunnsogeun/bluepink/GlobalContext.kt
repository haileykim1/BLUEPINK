package com.sogeunnsogeun.bluepink

import android.content.Context

abstract class GlobalContext {
    companion object {
        private lateinit var context: Context

        fun setContext(c: Context){
            context = c
        }

        fun getContext():Context{
            return context
        }
    }
}