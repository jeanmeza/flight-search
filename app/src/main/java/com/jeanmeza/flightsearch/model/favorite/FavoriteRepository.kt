package com.jeanmeza.flightsearch.model.favorite

import com.jeanmeza.flightsearch.data.FavoriteWithAirports
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAllFavoritesStream(): Flow<List<Favorite>>

    fun getAllFavouritesWithAirports(): Flow<List<FavoriteWithAirports>>

    fun getFavouriteStream(id: Int): Flow<Favorite?>

    suspend fun insertFavourite(favorite: Favorite)

    suspend fun updateFavourite(favorite: Favorite)

    suspend fun deleteFavourite(favorite: Favorite)
}