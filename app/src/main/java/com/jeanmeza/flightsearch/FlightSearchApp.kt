package com.jeanmeza.flightsearch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = {
                    query = it
                    onQueryChange(it)
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                onSearch = onSearch,
                placeholder = { Text("Enter departure airport") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            )
        },
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = it },
        tonalElevation = 0.dp
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            LazyColumn {
                items(
                    items = airportList,
                    key = { it.id },
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = it.iataCode)
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FlightSearchBarPreview() {
    FlightSearchTheme {
        Scaffold(
            topBar = {
                Column {
                    FlightSearchBar(
                        modifier = Modifier.fillMaxWidth(),
                        airportList = emptyList(),
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

