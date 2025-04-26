package com.jeanmeza.flightsearch.model.favourite

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "favourites")
data class Favourite(
    val id: Int = 0,
    @ColumnInfo(name = "departure_code")
    val departureCode: String,
    @ColumnInfo(name = "destination_code")
    val destinationCode: String,
)
