package de.martin.nanogiantschallenge.ui.detail

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.martin.nanogiantschallenge.R
import de.martin.nanogiantschallenge.data.model.Beer
import de.martin.nanogiantschallenge.util.Constants.INTENT_BEER_VALUE


class DetailActivity : AppCompatActivity() {
    private var beername: String = "Name not found"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val beer: Beer? = getBeerIntent(intent.extras)
        setUpView(beer)
    }

    @SuppressLint("SetTextI18n")
    private fun setUpView(beer: Beer?) {
        beername = beer?.name.toString()

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_foreground)

        val image: ImageView = findViewById(R.id.detail_beer_imageView)
        val detailBeerName: TextView = findViewById(R.id.detail_beer_name)
        val detailBrewingDesc: TextView = findViewById(R.id.detail_brewing_descripton)
        val detailBeerDescription: TextView = findViewById(R.id.detail_beer_description)
        val detailFoodPairings: TextView = findViewById(R.id.detail_food_pairings_description)
        val detailBrewingTip: TextView = findViewById(R.id.detail_brewing_tip_description)
        val detailAlcohol: Button = findViewById(R.id.detail_button_alcohol)

        Glide.with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load(beer?.image_url)
            .into(image)

        detailBeerName.text = beer?.name
        detailBrewingDesc.text = beer?.first_brewed
        detailBeerDescription.text = beer?.description
        detailFoodPairings.text = beer?.food_pairing?.joinToString(", ") ?: "No food pairings"
        detailBrewingTip.text = beer?.brewers_tips
        detailAlcohol.text = beer?.abv.toString() + "‰"
    }

    private fun getBeerIntent(extras: Bundle?): Beer? {
        if (extras != null) {
            return intent.getParcelableExtra(INTENT_BEER_VALUE)
        }
        return null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        useShareMenuItem()
        return super.onOptionsItemSelected(item)
    }

    private fun useShareMenuItem() {
        Toast.makeText(this, "Beer has been copied", Toast.LENGTH_SHORT).show()
        val clipboard: ClipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Beer", beername)
        clipboard.setPrimaryClip(clip)
    }
}
