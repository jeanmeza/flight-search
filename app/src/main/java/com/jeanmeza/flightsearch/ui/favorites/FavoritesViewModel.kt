package com.jeanmeza.flightsearch.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeanmeza.flightsearch.data.FavoriteWithAirports
import com.jeanmeza.flightsearch.model.favorite.FavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoritesViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val favoritesUiState: StateFlow<FavoritesUiState> =
        favoriteRepository.getAllFavouritesWithAirports().map { FavoritesUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FavoritesUiState(),
            )

}

data class FavoritesUiState(
    val favoritesList: List<FavoriteWithAirports> = listOf()
)
