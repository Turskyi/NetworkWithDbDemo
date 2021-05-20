package io.github.turskyi.networkwithdbdemo.data.api

import io.github.turskyi.networkwithdbdemo.data.entities.network.RestaurantResponse
import retrofit2.http.GET

interface RestaurantApi {

    companion object {
        const val BASE_URL = "https://random-data-api.com/api/"
    }

    @GET("restaurant/random_restaurant?size=20")
    suspend fun getRestaurants(): List<RestaurantResponse>
}