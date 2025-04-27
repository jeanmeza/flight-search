package com.jeanmeza.flightsearch.model.favourite

import com.jeanmeza.flightsearch.data.FavouriteWithAirports
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    fun getAllFavouritesStream(): Flow<List<Favourite>>

    fun getAllFavouritesWithAirports(): Flow<List<FavouriteWithAirports>>

    fun getFavouriteStream(id: Int): Flow<Favourite?>

    suspend fun insertFavourite(favourite: Favourite)

    suspend fun updateFavourite(favourite: Favourite)

    suspend fun deleteFavourite(favourite: Favourite)
}