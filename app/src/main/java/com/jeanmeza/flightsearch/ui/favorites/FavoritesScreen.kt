package com.jeanmeza.flightsearch.ui.favorites

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeanmeza.flightsearch.R
import com.jeanmeza.flightsearch.data.FavoriteWithAirports
import com.jeanmeza.flightsearch.data.FavoriteWithAirportsData
import com.jeanmeza.flightsearch.model.airport.Airport
import com.jeanmeza.flightsearch.ui.AppViewModelProvider
import com.jeanmeza.flightsearch.ui.navigation.NavigationDestination
import com.jeanmeza.flightsearch.ui.theme.FlightSearchTheme

object FavoritesDestination : NavigationDestination {
    override val route = "favorites"
}

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Column(modifier = modifier) {
        Text(stringResource(R.string.favorite_routes))

    }
}

@Composable
fun FavoriteCard(
    favorite: FavoriteWithAirports,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 8.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimensionResource(R.dimen.padding_medium))
                .padding(vertical = dimensionResource(R.dimen.padding_small))
        ) {
            Column(modifier = Modifier.weight(1f)) {
                AirportInfo(
                    title = R.string.depart,
                    airport = favorite.departure,
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                AirportInfo(
                    title = R.string.arrive,
                    airport = favorite.destination,
                )
            }
            IconButton(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterVertically),
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = stringResource(R.string.add_to_favorites)
                )
            }
        }
    }
}

@Composable
fun AirportInfo(
    @StringRes title: Int,
    airport: Airport,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_extra_small))
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.labelSmall,
        )
        Row {
            Text(
                text = airport.iataCode,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_small)))
            Text(
                text = airport.name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview(apiLevel = 36)
@Composable
fun FavoriteCardPreview() {
    FlightSearchTheme {
        FavoriteCard(favorite = FavoriteWithAirportsData.favoritesList.get(0))
    }
}

@Preview(apiLevel = 36, showBackground = true, showSystemUi = true)
@Composable
fun FavoriteScreenPreview() {
    FlightSearchTheme {
        FavoritesScreen()
    }
}
