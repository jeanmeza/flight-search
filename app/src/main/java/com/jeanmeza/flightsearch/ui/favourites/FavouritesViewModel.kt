package com.jeanmeza.flightsearch.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeanmeza.flightsearch.model.favourite.Favourite
import com.jeanmeza.flightsearch.model.favourite.FavouriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavouritesViewModel(private val favouriteRepository: FavouriteRepository) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val favouritesUiState: StateFlow<FavouritesUiState> =
        favouriteRepository.getAllFavouritesStream().map { FavouritesUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FavouritesUiState(),
            )

}

data class FavouritesUiState(
    val favouritesList: List<Favourite> = listOf()
)