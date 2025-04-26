package com.jeanmeza.flightsearch.model.favourite

import kotlinx.coroutines.flow.Flow

class OfflineFavouriteRepository(private val favouriteDao: FavouriteDao) : FavouriteRepository {
    override fun getAllFavouritesStream(): Flow<List<Favourite>> = favouriteDao.getAllFavourites()

    override fun getFavouriteStream(id: Int): Flow<Favourite?> = favouriteDao.getFavourite(id)

    override suspend fun insertFavourite(favourite: Favourite) = favouriteDao.insert(favourite)

    override suspend fun updateFavourite(favourite: Favourite) = favouriteDao.update(favourite)

    override suspend fun deleteFavourite(favourite: Favourite) = favouriteDao.delete(favourite)
}