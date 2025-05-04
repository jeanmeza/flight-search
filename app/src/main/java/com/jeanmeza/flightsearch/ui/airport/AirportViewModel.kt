package com.jeanmeza.flightsearch.ui.airport

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeanmeza.flightsearch.model.airport.Airport
import com.jeanmeza.flightsearch.model.airport.AirportRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class AirportViewModel(
    savedStateHandle: SavedStateHandle,
    private val airportRepository: AirportRepository,
) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    private val airportId: Int = checkNotNull(savedStateHandle[AirportDestination.AIRPORT_ID_ARG])
    private val airportFlow = airportRepository.getAirportStream(airportId)
    private val relatedAirportsFlow = flow {
        emit(airportRepository.getAllAirportsExcept(airportId))
    }

    val uiState: StateFlow<AirportUiState> =
        combine(airportFlow, relatedAirportsFlow) { airport, otherAirports ->
            AirportUiState(
                airport = airport,
                destinations = otherAirports.take(5)
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = AirportUiState(),
        )

}

data class AirportUiState(
    val airport: Airport? = null,
    val destinations: List<Airport> = emptyList()
)