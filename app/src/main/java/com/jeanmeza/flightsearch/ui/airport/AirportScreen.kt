package com.jeanmeza.flightsearch.ui.airport

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeanmeza.flightsearch.R
import com.jeanmeza.flightsearch.ui.AppViewModelProvider
import com.jeanmeza.flightsearch.ui.components.FlightInfoCard
import com.jeanmeza.flightsearch.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object AirportDestination : NavigationDestination {
    override val route: String = "airport"
    const val AIRPORT_ID_ARG = "airportId"
    val routeWithArgs = "$route/{$AIRPORT_ID_ARG}"
}

@Composable
fun AirportScreen(
    modifier: Modifier = Modifier,
    viewModel: AirportViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val uiState by viewModel.uiState.collectAsState()
    val airportName = if (uiState.airport == null) "" else uiState.airport!!.iataCode
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.flights_from, airportName),
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        )
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            items(items = uiState.destinations, key = { it.id }) { destination ->
                FlightInfoCard(
                    departure = uiState.airport!!,
                    destination = destination,
                    onAddFavorite = { dep, dest ->
                        coroutineScope.launch {
                            viewModel.favoriteItem(dep, dest)
                        }
                    },
                    onRemoveFavorite = {
                        coroutineScope.launch {
                            viewModel.removeFavorite(it)
                        }
                    },
                )
            }
        }
    }
}