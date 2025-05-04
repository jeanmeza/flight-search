package com.jeanmeza.flightsearch.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeanmeza.flightsearch.model.airport.Airport
import com.jeanmeza.flightsearch.model.airport.AirportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchBarViewModel(
    private val airportRepository: AirportRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchBarUiState())
    val uiState = _uiState.asStateFlow()

    fun searchAirports(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                currentState.copy(result = airportRepository.searchAirport(query))
            }
            Log.d("STATE", _uiState.value.result.toString())
        }
    }
}

data class SearchBarUiState(
    val result: List<Airport> = emptyList()
)