package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_play.view.*

class PlayAdapter(private val plays: List<Play>) : RecyclerView.Adapter<PlayAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_play, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return plays.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(plays[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(play: Play) {
          //  itemView.tvName.text = product.productText
          //  itemView.tvQuantity.text = product.quantity.toString()
        }
    }


}