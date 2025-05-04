package com.jeanmeza.flightsearch.ui.favorites

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

object FavoritesDestination : NavigationDestination {
    override val route = "favorites"
}

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.favoritesUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.favorite_routes),
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        )
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            items(items = uiState.favoritesList, key = { it.favorite.id }) {
                FlightInfoCard(
                    favourite = it.favorite,
                    departure = it.departure,
                    destination = it.destination,
                    onAddFavorite = { dep, dest -> },
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
