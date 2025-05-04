package com.jeanmeza.flightsearch.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
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
import com.jeanmeza.flightsearch.ui.airport.FlightsScreen
import com.jeanmeza.flightsearch.ui.favorites.FavoritesDestination
import com.jeanmeza.flightsearch.ui.favorites.FavoritesScreen

@Composable
fun FlightSearchNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = FavoritesDestination.route,
        modifier = modifier,
    ) {
        composable(route = FavoritesDestination.route) {
            FavoritesScreen(
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
        composable(
            route = AirportDestination.routeWithArgs,
            arguments = listOf(navArgument(AirportDestination.AIRPORT_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            FlightsScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.padding_small)),
                onBack = {
                    navController.popBackStack(FavoritesDestination.route, inclusive = false)
                }
            )
        }
    }
}