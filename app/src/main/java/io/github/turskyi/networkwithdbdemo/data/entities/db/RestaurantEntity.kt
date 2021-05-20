package io.github.turskyi.networkwithdbdemo.data.entities.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class RestaurantEntity(
    @PrimaryKey val name: String,
    val type: String,
    val logo: String,
    val address: String
)