package com.daesin.school.noticeAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daesin.school.R

class NoticeAdapater(private val noticeList: ArrayList<NoticeData>): RecyclerView.Adapter<NoticeAdapater.CustomViewHolder>() {

    inner class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.notice_title)
        val writer: TextView = itemView.findViewById(R.id.notice_writer)
        val date: TextView = itemView.findViewById(R.id.notice_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notice_recycler, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noticeList.size
    }

    override fun onBindViewHolder(holder: NoticeAdapater.CustomViewHolder, position: Int) {
        holder.title.text = noticeList[position].title
        holder.writer.text = noticeList[position].writer
        holder.date.text = noticeList[position].date
    }

}