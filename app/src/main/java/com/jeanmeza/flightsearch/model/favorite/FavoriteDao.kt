package com.jeanmeza.flightsearch.model.favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.jeanmeza.flightsearch.data.FavoriteWithAirports
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite ORDER BY id ASC")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Transaction
    @Query("SELECT * FROM favorite ORDER BY id ASC")
    fun getAllFavoritesWithAirports(): Flow<List<FavoriteWithAirports>>

    @Query("SELECT * FROM favorite WHERE id = :id")
    fun getFavorite(id: Int): Flow<Favorite>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorite: Favorite)

    @Update
    suspend fun update(favorite: Favorite)

    @Delete
    suspend fun delete(favorite: Favorite)
}