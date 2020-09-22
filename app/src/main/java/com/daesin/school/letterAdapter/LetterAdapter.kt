package com.daesin.school.letterAdapter

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daesin.school.R
import com.daesin.school.letterAdapter.LetterData
import kotlinx.android.synthetic.main.letter_item.view.*

class LetterAdapater(private val letterList: ArrayList<LetterData>): RecyclerView.Adapter<LetterAdapater.CustomViewHolder>() {

    inner class CustomViewHolder(itemView: ViewGroup): RecyclerView.ViewHolder(LayoutInflater.from(itemView.context).inflate(R.layout.letter_item, itemView, false))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CustomViewHolder(parent)

    override fun getItemCount(): Int = letterList.size

    override fun onBindViewHolder(holder: LetterAdapater.CustomViewHolder, position: Int) {
        with(letterList[position]) {
            with(holder.itemView) {
                if (file)
                    letter_file.visibility = View.VISIBLE
                letter_title.text = title
                letter_writer.text = writer
                letter_date.text = date
                //웹창이동
                letter.setOnClickListener {
                    Log.d("letter Intent", link)
                    it.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://school.busanedu.net${link}")))
                }
            }
        }
    }

}