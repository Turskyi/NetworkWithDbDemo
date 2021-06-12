package io.github.turskyi.networkwithdbdemo.data.repository

import androidx.room.withTransaction
import io.github.turskyi.networkwithdbdemo.data.api.RestaurantApi
import io.github.turskyi.networkwithdbdemo.data.db.RestaurantDatabase
import io.github.turskyi.networkwithdbdemo.data.entities.db.RestaurantEntity
import io.github.turskyi.networkwithdbdemo.common.Resource
import io.github.turskyi.networkwithdbdemo.data.util.getNetworkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val netSource: RestaurantApi,
    private val db: RestaurantDatabase
) {
    private val dbSource = db.restaurantDao()

    fun getRestaurants(): Flow<Resource<List<RestaurantEntity>>> = getNetworkBoundResource(
        query = { dbSource.getAllRestaurants() },
        fetch = { netSource.getRestaurants() },
        saveFetchResult = { restaurantsResponse ->
            db.withTransaction {
                dbSource.deleteAllRestaurants()
                dbSource.insertRestaurants(restaurantsResponse.map { it.mapToDb() })
            }
        }
    )
}