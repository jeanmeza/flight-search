package com.jeanmeza.flightsearch.model.favourite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.jeanmeza.flightsearch.data.FavouriteWithAirports
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {
    @Query("SELECT * FROM favourites ORDER BY id ASC")
    fun getAllFavourites(): Flow<List<Favourite>>

    @Transaction
    @Query("SELECT * FROM favourites ORDER BY id ASC")
    fun getAllFavouritesWithAirports(): Flow<List<FavouriteWithAirports>>

    @Query("SELECT * FROM favourites WHERE id = :id")
    fun getFavourite(id: Int): Flow<Favourite>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favourite: Favourite)

    @Update
    suspend fun update(favourite: Favourite)

    @Delete
    suspend fun delete(favourite: Favourite)
}