package de.martin.nanogiantschallenge.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.martin.nanogiantschallenge.R
import de.martin.nanogiantschallenge.api.model.Beer

class BeerAdapter :
    RecyclerView.Adapter<BeerAdapter.ViewHolder>() {
    var beerList: List<Beer> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = beerList[position]
        holder.name.text = beer.name
    }

    override fun getItemCount(): Int {
        return beerList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: ArrayList<Beer>) {
        beerList = list
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.list_item_beer_name)
    }
}
