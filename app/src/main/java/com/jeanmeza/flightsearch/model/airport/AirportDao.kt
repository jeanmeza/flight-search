package com.jeanmeza.flightsearch.model.airport

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query("SELECT * FROM airports ORDER BY name ASC")
    fun getAllAirports(): Flow<List<Airport>>

    @Query("SELECT * FROM airports WHERE id = :id")
    fun getAirport(id: Int): Flow<Airport>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(airport: Airport)

    @Update
    suspend fun update(airport: Airport)

    @Delete
    suspend fun delete(airport: Airport)
}