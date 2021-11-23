package de.martin.nanogiantschallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.martin.nanogiantschallenge.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private var recyclerView: RecyclerView? = null
    private var beerAdapter: BeerAdapter? = null

    //TODO add search
    //TODO add httpok chaching
    //TODO    Request states loading etc.
    //TODO    Animation list/transformActivity
    //TODO    Material themes/ light dark mode
    //TODO    DI
    //TODO    Tests

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            beerAdapter = BeerAdapter()
            adapter = beerAdapter
        }

        viewModel = ViewModelProvider(this)[MainViewModel::class.java].apply {
            getBeers()
        }

        viewModel.beers.observe(this, {
            beerAdapter?.submitList(it)
        })
    }
}