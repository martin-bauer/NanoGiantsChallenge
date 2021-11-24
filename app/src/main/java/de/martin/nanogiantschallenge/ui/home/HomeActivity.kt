package de.martin.nanogiantschallenge.ui.home

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import de.martin.nanogiantschallenge.R
import de.martin.nanogiantschallenge.viewmodel.home.HomeViewModel


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var viewModel: HomeViewModel
    private var recyclerView: RecyclerView? = null
    private var beerAdapter: BeerAdapter? = null
    var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java].apply {
            getBeers()
        }
        viewModel.beers.observe(this, {
            beerAdapter?.submitList(it)
        })
        initRecyclerView(viewModel)
    }

    private fun initRecyclerView(viewModel: HomeViewModel) {
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            beerAdapter = BeerAdapter()
            adapter = beerAdapter
        }
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1) && searchView?.isIconified == true) {
                    viewModel.searchNextPage()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val search = menu?.findItem(R.id.search)
        searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        recyclerView = null
        beerAdapter = null
        searchView = null
    }

    override fun onQueryTextChange(query: String?): Boolean {
        beerAdapter?.submitList(query?.let { viewModel.searchList(it) })
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }
}
