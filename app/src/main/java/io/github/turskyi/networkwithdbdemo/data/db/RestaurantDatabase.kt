package io.github.turskyi.networkwithdbdemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.turskyi.networkwithdbdemo.data.entities.db.RestaurantEntity

@Database(entities = [RestaurantEntity::class], version = 1)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao
}