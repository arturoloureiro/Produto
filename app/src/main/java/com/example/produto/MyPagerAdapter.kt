package com.example.produto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.widget.ImageView

data class Model(val title: String, val desc: String, val imageResId: Int)
class MyPagerAdapter(private val items: List<Model>) :
    RecyclerView.Adapter<MyPagerAdapter.PagerViewHolder>() {
    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {


        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        return PagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val item = items[position]
        val titletextView = holder.itemView.findViewById<TextView>(R.id.textView)
        val descTextView = holder.itemView.findViewById<TextView>(R.id.textView)
        val imageiem = holder.itemView.findViewById<TextView>(R.id.textView)

        titletextView.text = item.title
        descTextView.text = item.desc
        imageiem.setImageResource(item.imageResId)
    }

}

