package com.jeanmeza.flightsearch.model.airport

import kotlinx.coroutines.flow.Flow

interface AirportRepository {
    fun getAllAirportsStream(): Flow<List<Airport>>

    fun getAirportStream(id: Int): Flow<Airport?>

    suspend fun searchAirport(query: String): List<Airport>

    suspend fun insertAirport(airport: Airport)

    suspend fun updateAirport(airport: Airport)

    suspend fun deleteAirport(airport: Airport)
}
