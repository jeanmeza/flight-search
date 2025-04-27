package com.jeanmeza.flightsearch.ui.favourites

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.jeanmeza.flightsearch.R
import com.jeanmeza.flightsearch.data.FavouriteWithAirports
import com.jeanmeza.flightsearch.data.FavouriteWithAirportsData
import com.jeanmeza.flightsearch.model.airport.Airport
import com.jeanmeza.flightsearch.ui.theme.FlightSearchTheme

@Composable
fun FavouritesScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(stringResource(R.string.favourite_routes))

    }
}

@Composable
fun FavouriteCard(
    favourite: FavouriteWithAirports,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimensionResource(R.dimen.padding_medium))
                .padding(vertical = dimensionResource(R.dimen.padding_small))
        ) {
            Column(modifier = Modifier.weight(1f)) {
                AirportInfo(
                    title = R.string.depart,
                    airport = favourite.departure,
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                AirportInfo(
                    title = R.string.arrive,
                    airport = favourite.destination,
                )
            }
            IconButton(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterVertically),
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = stringResource(R.string.add_to_favourites)
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

@Preview(apiLevel = 36, showBackground = true)
@Composable
fun FavouriteCardPreview() {
    FlightSearchTheme {
        FavouriteCard(favourite = FavouriteWithAirportsData.favouritesList.get(0))
    }
}

@Preview(apiLevel = 36, showBackground = true, showSystemUi = true)
@Composable
fun FavouriteScreenPreview() {
    FlightSearchTheme {
        FavouritesScreen()
    }
}
