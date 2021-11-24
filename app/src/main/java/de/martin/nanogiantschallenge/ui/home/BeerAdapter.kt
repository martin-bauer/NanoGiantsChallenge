package de.martin.nanogiantschallenge.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.martin.nanogiantschallenge.R
import de.martin.nanogiantschallenge.data.model.Beer
import de.martin.nanogiantschallenge.ui.detail.DetailActivity
import de.martin.nanogiantschallenge.util.Constants.INTENT_BEER_VALUE

class BeerAdapter :
    RecyclerView.Adapter<BeerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.list_item_beer_name)
        val card: CardView = itemView.findViewById(R.id.list_item_card)
        val tagline: TextView = itemView.findViewById(R.id.list_item_beer_tagline)
        val image: ImageView = itemView.findViewById(R.id.list_item_beer_image)
    }

    private var beerList: List<Beer> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = beerList[position]
        holder.name.text = beer.name
        holder.tagline.text = beer.tagline

        setupGlide(holder, beer)
        setAnimation(holder.card.context, holder.itemView)

        holder.card.setOnClickListener {
            createIntent(it, beer)
        }
    }

    private fun createIntent(view: View, beer: Beer) {
        val intent = Intent(view.context, DetailActivity::class.java)
        intent.putExtra(INTENT_BEER_VALUE, beer)
        view.context.startActivity(intent)
    }

    private fun setupGlide(holder: ViewHolder, beer: Beer) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_foreground)
        Glide.with(holder.itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(beer.image_url)
            .into(holder.image)
    }

    private fun setAnimation(context: Context, itemView: View) {
        val animation: Animation =
            AnimationUtils.loadAnimation(context, R.anim.recycler_animation)
        itemView.startAnimation(animation)
    }

    override fun getItemCount(): Int {
        return beerList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: ArrayList<Beer>?) {
        if (list != null) {
            beerList = list
            notifyDataSetChanged()
        }
    }
}
