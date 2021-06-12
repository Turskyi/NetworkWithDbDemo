package io.github.turskyi.networkwithdbdemo.features.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.turskyi.networkwithdbdemo.common.Resource
import io.github.turskyi.networkwithdbdemo.data.entities.db.RestaurantEntity
import io.github.turskyi.networkwithdbdemo.data.repository.RestaurantRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    repository: RestaurantRepositoryImpl
) : ViewModel() {
    /* "asLiveData" will internally launch a coroutine,
    * and we use it because it is easier to use between
    * viewModel and ui layer */
    val restaurants: LiveData<Resource<List<RestaurantEntity>>> =
        repository.getRestaurants().asLiveData()
}