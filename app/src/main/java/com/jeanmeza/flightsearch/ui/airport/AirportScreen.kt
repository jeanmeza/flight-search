package com.jeanmeza.flightsearch.ui.airport

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jeanmeza.flightsearch.ui.navigation.NavigationDestination

object AirportDestination : NavigationDestination {
    override val route: String = "airport"
    const val AIRPORT_ID_ARG = "airportId"
    val routeWithArgs = "$route/{$AIRPORT_ID_ARG}"
}

@Composable
fun AirportScreen(modifier: Modifier = Modifier) {
    // TODO: add lazy column of cards
    //  The AirportInfo card from used in the
}