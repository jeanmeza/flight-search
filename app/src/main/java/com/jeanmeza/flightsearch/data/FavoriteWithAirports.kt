package com.jeanmeza.flightsearch.data

import androidx.room.Embedded
import androidx.room.Relation
import com.jeanmeza.flightsearch.model.airport.Airport
import com.jeanmeza.flightsearch.model.favorite.Favorite

data class FavoriteWithAirports(
    @Embedded
    val favorite: Favorite,
    @Relation(
        parentColumn = "departure_code",
        entityColumn = "iata_code"
    )
    val departure: Airport,
    @Relation(
        parentColumn = "destination_code",
        entityColumn = "iata_code"
    )
    val destination: Airport
)

object FavoriteWithAirportsData {
    val favoritesList = listOf<FavoriteWithAirports>(
        FavoriteWithAirports(
            favorite = Favorite(1, "FCO", "CPH"),
            departure = Airport(1, "FCO", "Leonardo da Vinci International Airport", 12341234),
            destination = Airport(2, "CPH", "Copenhagen Airport", 12341234),
        ),
        FavoriteWithAirports(
            favorite = Favorite(2, "OSL", "LIS"),
            departure = Airport(3, "OSL", "Oslo Airport", 12341234),
            destination = Airport(4, "LIS", "Humberto Delgado Airport", 12341234),
        ),
    )
}