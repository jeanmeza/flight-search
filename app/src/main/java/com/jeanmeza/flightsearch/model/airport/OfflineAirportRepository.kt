package com.jeanmeza.flightsearch.model.airport

import kotlinx.coroutines.flow.Flow

class OfflineAirportRepository(private val airportDao: AirportDao) : AirportRepository {
    override fun getAllAirportsStream(): Flow<List<Airport>> = airportDao.getAllAirports()

    override fun getAirportStream(id: Int): Flow<Airport?> = airportDao.getAirport(id)

    override suspend fun getAllAirportsExcept(id: Int): List<Airport> =
        airportDao.getAllAirportsExcept(id)

    override suspend fun searchAirport(query: String): List<Airport> =
        airportDao.searchAirport(query)

    override suspend fun insertAirport(airport: Airport) = airportDao.insert(airport)

    override suspend fun updateAirport(airport: Airport) = airportDao.update(airport)

    override suspend fun deleteAirport(airport: Airport) = airportDao.delete(airport)
}