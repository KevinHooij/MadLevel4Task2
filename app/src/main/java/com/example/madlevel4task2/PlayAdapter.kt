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

            if (play.outcome == Play.Outcome.WIN){
                itemView.tvOutcome.text = itemView.context.resources.getString(R.string.win_txt)
            }

            if (play.outcome == Play.Outcome.LOSE){
                itemView.tvOutcome.text = itemView.context.resources.getString(R.string.lose_txt)
            }

            if (play.outcome == Play.Outcome.DRAW){
                itemView.tvOutcome.text = itemView.context.resources.getString(R.string.draw_txt)
            }


            if (play.yourAttack == Play.Attack.ROCK){
                itemView.ivPlayerHist.setImageResource(R.drawable.rock)
            }
            if (play.yourAttack == Play.Attack.PAPER){
                itemView.ivPlayerHist.setImageResource(R.drawable.paper)
            }
            if (play.yourAttack == Play.Attack.SCISSORS){
                itemView.ivPlayerHist.setImageResource(R.drawable.scissors)
            }


            if (play.enemyAttack == Play.Attack.ROCK){
                itemView.ivEnemyHist.setImageResource(R.drawable.rock)
            }
            if (play.enemyAttack == Play.Attack.PAPER){
                itemView.ivEnemyHist.setImageResource(R.drawable.paper)
            }
            if (play.enemyAttack == Play.Attack.SCISSORS){
                itemView.ivEnemyHist.setImageResource(R.drawable.scissors)
            }

            itemView.tvDate.text = play.date.toString()

        }
    }


}