package com.jeanmeza.flightsearch.model.favorite

import com.jeanmeza.flightsearch.data.FavoriteWithAirports
import kotlinx.coroutines.flow.Flow

class OfflineFavoriteRepository(private val favoriteDao: FavoriteDao) : FavoriteRepository {
    override fun getAllFavoritesStream(): Flow<List<Favorite>> = favoriteDao.getAllFavorites()

    override fun getAllFavouritesWithAirports(): Flow<List<FavoriteWithAirports>> =
        favoriteDao.getAllFavoritesWithAirports()

    override fun getFavouriteStream(id: Int): Flow<Favorite?> = favoriteDao.getFavorite(id)

    override suspend fun insertFavourite(favorite: Favorite) = favoriteDao.insert(favorite)

    override suspend fun updateFavourite(favorite: Favorite) = favoriteDao.update(favorite)

    override suspend fun deleteFavourite(favorite: Favorite) = favoriteDao.delete(favorite)
}