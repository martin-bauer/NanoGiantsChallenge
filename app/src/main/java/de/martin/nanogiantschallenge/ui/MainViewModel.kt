package de.martin.nanogiantschallenge.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.martin.nanogiantschallenge.api.model.Beer
import de.martin.nanogiantschallenge.repository.BeerRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var repository: BeerRepository = BeerRepository()

    private var _beers: MutableLiveData<ArrayList<Beer>> = MutableLiveData()
    var beers = _beers

    fun getBeers() {
        viewModelScope.launch {
            val response = repository.getBeers()
            _beers.value = response
        }
    }
}