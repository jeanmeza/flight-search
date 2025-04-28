package com.jeanmeza.flightsearch.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jeanmeza.flightsearch.R
import com.jeanmeza.flightsearch.ui.airport.AirportDestination
import com.jeanmeza.flightsearch.ui.airport.AirportScreen
import com.jeanmeza.flightsearch.ui.favourites.FavouritesDestination
import com.jeanmeza.flightsearch.ui.favourites.FavouritesScreen

@Composable
fun FlightSearchNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = FavouritesDestination.route,
        modifier = modifier,
    ) {
        composable(route = FavouritesDestination.route) {
            FavouritesScreen(
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
        }
        composable(
            route = AirportDestination.routeWithArgs,
            arguments = listOf(navArgument(AirportDestination.AIRPORT_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            AirportScreen(
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}