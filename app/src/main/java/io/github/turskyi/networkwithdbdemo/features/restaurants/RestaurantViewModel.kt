package io.github.turskyi.networkwithdbdemo.features.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.turskyi.networkwithdbdemo.data.api.RestaurantApi
import io.github.turskyi.networkwithdbdemo.data.entities.network.RestaurantResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    api: RestaurantApi
) : ViewModel() {
        private val restaurantsLiveData = MutableLiveData<List<RestaurantResponse>>()
        val restaurants: LiveData<List<RestaurantResponse>> = restaurantsLiveData

    init {
        viewModelScope.launch {
            val restaurants = api.getRestaurants()
//          delay is added  only for testing purpose to see the progress bar
            delay(2000)
            restaurantsLiveData.value = restaurants
        }
    }
}