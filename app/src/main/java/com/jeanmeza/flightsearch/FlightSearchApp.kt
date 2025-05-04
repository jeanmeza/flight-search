package com.jeanmeza.flightsearch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jeanmeza.flightsearch.model.airport.Airport
import com.jeanmeza.flightsearch.ui.AppViewModelProvider
import com.jeanmeza.flightsearch.ui.SearchBarViewModel
import com.jeanmeza.flightsearch.ui.navigation.FlightSearchNavHost
import com.jeanmeza.flightsearch.ui.theme.FlightSearchTheme

@Composable
fun FlightSearchApp(
    navController: NavHostController = rememberNavController(),
    viewModel: SearchBarViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val searchBarUiState = viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            FlightSearchBar(
                modifier = Modifier.fillMaxWidth(),
                airportList = searchBarUiState.value.result,
                onQueryChange = viewModel::searchAirports,
                onSearch = viewModel::searchAirports,
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            FlightSearchNavHost(navController)
        }
    }
}

@Composable
fun FlightSearchBar(
    modifier: Modifier = Modifier,
    airportList: List<Airport> = emptyList(),
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
) {
    var query by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }
    val horizontalPadding =
        dimensionResource(if (expanded) R.dimen.no_padding else R.dimen.padding_medium)
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = {
                    query = it
                    onQueryChange(it)
                },
                onSearch = onSearch,
                expanded = expanded,
                onExpandedChange = { expanded = it },
                placeholder = { Text("Enter departure airport") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            )
        },
        modifier = modifier.padding(horizontal = horizontalPadding),
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(
                contentPadding = PaddingValues(
                    horizontal = dimensionResource(R.dimen.padding_medium),
                    vertical = dimensionResource(R.dimen.padding_small)
                ),
            ) {
                items(
                    items = airportList,
                    key = { it.id },
                ) {
                    AirportItem(
                        airport = it,
                        onClick = {
                            expanded = false
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun AirportItem(
    modifier: Modifier = Modifier,
    airport: Airport,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors().copy(containerColor = Color.Transparent)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = airport.iataCode,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier.width(dimensionResource(R.dimen.padding_small)))
            Text(
                text = airport.name,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FlightSearchBarPreview() {
    val airportList = listOf(
        Airport(1, "FCO", "Leonardo da Vinci International Airport", 12341234),
        Airport(2, "VIE", "Vienna International Airport", 12341234),
    )
    FlightSearchTheme {
        Scaffold(
            topBar = {
                Column {
                    FlightSearchBar(
                        modifier = Modifier.fillMaxWidth(),
                        airportList = airportList,
                        onQueryChange = {},
                        onSearch = {},
                    )
                }
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
            }
        }
    }
}

