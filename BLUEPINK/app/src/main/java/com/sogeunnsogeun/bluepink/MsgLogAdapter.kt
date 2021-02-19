package com.sogeunnsogeun.bluepink

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MsgLogAdapter(var items:List<MessageItem>) : RecyclerView.Adapter<MsgLogAdapter.MsgLogViewHolder>() {
    interface OnItemClickListener{
        fun OnItemClick(holder:MsgLogViewHolder, view: View, data:MessageItem, position:Int)
    }

    var itemClickListener: OnItemClickListener?=null

    inner class MsgLogViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        var logSender: TextView = itemView.findViewById(R.id.log_sender)
        var logContent: TextView = itemView.findViewById(R.id.log_content)

        init{
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgLogViewHolder {
        val v:View = LayoutInflater.from(parent.context).inflate(R.layout.log_row, parent, false)
        return MsgLogViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MsgLogViewHolder, position: Int) {
        if(items[position].isChatbots == true)
            holder.logSender.text= "챗봇"
        else
            holder.logSender.text = UserInfo.get(UserInfo.NAME).toString()

        holder.logContent.text = items[position].msgContent
    }

}