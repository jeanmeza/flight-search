package com.jeanmeza.flightsearch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jeanmeza.flightsearch.ui.favourites.FavouritesScreen

@Composable
fun FlightSearchApp() {
    Scaffold(
        topBar = { FlightSearchTopAppBar() }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // TODO: add search bar here
            FavouritesScreen()
        }
    }
}

@Composable
fun FlightSearchTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
    )
}