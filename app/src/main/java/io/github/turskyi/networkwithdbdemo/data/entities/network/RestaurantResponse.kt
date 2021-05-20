package io.github.turskyi.networkwithdbdemo.data.entities.network

import io.github.turskyi.networkwithdbdemo.data.entities.db.RestaurantEntity

data class RestaurantResponse(
    val name: String,
    val type: String,
    val logo: String,
    val address: String
) {
    fun mapToDb() = RestaurantEntity(name, type, logo, address)
}
