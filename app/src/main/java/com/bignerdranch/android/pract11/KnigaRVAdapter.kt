package com.bignerdranch.android.pract11

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.pract11.data.models.KnigaTypes

class KnigaRVAdapter(context: Context, val data: MutableList<KnigaTypes>) : RecyclerView.Adapter<KnigaRVAdapter.KnigaHolder?>() {

    private  val layoutInflater: LayoutInflater = android.view.LayoutInflater.from(context)

    private var iClickListener : ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnigaHolder {
        val view: View = layoutInflater.inflate(R.layout.item_kniga, parent, false)
        return KnigaHolder(view)
    }

    override fun onBindViewHolder(holder: KnigaHolder, position: Int) {
        val item = data[position]
        holder.nameTextView.text = item.name
        holder.authorTextView.text = item.author
        holder.pagesTextView.text = item.numPages
    }

    override fun getItemCount(): Int = data.size

    inner class KnigaHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var nameTextView: TextView = itemView.findViewById(R.id.tvName)
        var authorTextView: TextView = itemView.findViewById(R.id.tvAuthor)
        var pagesTextView: TextView = itemView.findViewById(R.id.tvKolvostranic)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(view: View?){
            iClickListener?.onItemClick(view, adapterPosition)
        }
    }
    fun setClickListener(itemClickListener: ItemClickListener?){
        iClickListener = itemClickListener
    }

    interface ItemClickListener{
        fun onItemClick(view: View?, position: Int)
    }
}