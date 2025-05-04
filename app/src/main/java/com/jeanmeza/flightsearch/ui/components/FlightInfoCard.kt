package com.jeanmeza.flightsearch.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.jeanmeza.flightsearch.R
import com.jeanmeza.flightsearch.model.airport.Airport
import com.jeanmeza.flightsearch.ui.favorites.AirportInfo

@Composable
fun FlightInfoCard(
    departure: Airport,
    destination: Airport,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = dimensionResource(R.dimen.no_padding),
            topEnd = dimensionResource(R.dimen.padding_small),
            bottomStart = dimensionResource(R.dimen.no_padding),
            bottomEnd = dimensionResource(R.dimen.no_padding),
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
                    airport = departure,
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                AirportInfo(
                    title = R.string.arrive,
                    airport = destination,
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
