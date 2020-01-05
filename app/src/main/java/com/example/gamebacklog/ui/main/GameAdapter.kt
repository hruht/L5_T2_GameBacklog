package com.example.gamebacklog.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import kotlinx.android.synthetic.main.item_card.view.*


class GameAdapter(private val games: ArrayList<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    lateinit var context: Context
    private var monthString: String = " "

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return games.size
    }

    /**
     * Creates and returns a ViewHolder object
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_card, parent, false))
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //private val tv_name: TextView = itemView.findViewById(android.R.id.text1)
        //private val tv_platform: TextView = itemView.findViewById(android.R.id.text1)
        //private val tv_releasedate: TextView = itemView.findViewById(android.R.id.text1)

        fun bind(game: Game) {
            checkMonth(game.month)
            itemView.tv_name.text = game.title
            itemView.tv_platform.text = game.platform
            itemView.tv_releasedate.text = ("Release: " + game.day + " " + monthString + " " + game.year)
        }

    }

    private fun checkMonth(month: Int){

        when (month) {
            1 -> monthString = "January"
            2 -> monthString = "February"
            3 -> monthString = "March"
            4 -> monthString = "April"
            5 -> monthString = "May"
            6 -> monthString = "June"
            7 -> monthString = "July"
            8 -> monthString = "August"
            9 -> monthString = "September"
            10 -> monthString = "October"
            11 -> monthString = "November"
            12 -> monthString = "December"
            else -> {
                monthString = "Errember"
            }
        }
    }
}