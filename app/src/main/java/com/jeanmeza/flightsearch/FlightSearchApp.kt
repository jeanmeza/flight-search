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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jeanmeza.flightsearch.ui.navigation.FlightSearchNavHost

@Composable
fun FlightSearchApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            FlightSearchTopAppBar()
            // TODO: add search bar here
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            FlightSearchNavHost(navController)
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

