package com.example.cinema.presentation.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cinema.R
import com.ramcosta.composedestinations.generated.destinations.MoviesScreenDestination
import com.ramcosta.composedestinations.generated.destinations.ProfileScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    Movies(MoviesScreenDestination, Icons.Default.Home, R.string.movies_screen),
    Profile(ProfileScreenDestination, Icons.Default.Person, R.string.profile_screen),
}