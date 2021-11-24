package de.martin.nanogiantschallenge.viewmodel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.martin.nanogiantschallenge.data.model.Beer
import de.martin.nanogiantschallenge.repository.BeerRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: BeerRepository
) : ViewModel() {
    private val perPage = 50
    private var page = 1
    private var _beers: MutableLiveData<ArrayList<Beer>> = MutableLiveData()
    var beers: LiveData<ArrayList<Beer>> = _beers

    fun getBeers() {
        viewModelScope.launch {
            _beers.value = makeBeerCall()
        }
    }

    fun searchList(searchText: String): ArrayList<Beer>? {
        return _beers.value?.filter {
            it.name!!.contains(
                searchText,
                true
            )
        }?.toCollection(ArrayList())
    }

    fun searchNextPage() {
        viewModelScope.launch {
            val response = makeBeerCall()
            val newBeerList = _beers.value
            newBeerList?.addAll(response)
            _beers.postValue(newBeerList)
        }
    }

    private suspend fun makeBeerCall(): ArrayList<Beer> {
        val response = this.repository.getBeers(page.toString(), perPage.toString())
        page += 1
        return response
    }
}
