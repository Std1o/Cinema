package com.example.cinema.presentation.ui.screens.main

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.cinema.presentation.ui.navigation.BottomBarDestination
import com.example.cinema.presentation.ui.theme.Purple80
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import com.ramcosta.composedestinations.utils.startDestination

@Composable
fun BottomBar(navController: NavController) {
    val currentDestination: DestinationSpec = navController.currentDestinationAsState().value
        ?: NavGraphs.root.startDestination

    BottomNavigation(backgroundColor = Color.Black) {
        BottomBarDestination.entries.forEach { destination ->
            val selected = currentDestination == destination.direction
            BottomNavigationItem(
                selected = selected,
                onClick = {
                    navController.navigate(destination.direction.startDestination.route) {
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        destination.icon,
                        contentDescription = stringResource(destination.label),
                        tint = if (selected) Purple80 else Color.LightGray
                    )
                },
                label = {
                    Text(
                        stringResource(destination.label),
                        color = if (selected) Purple80 else Color.LightGray
                    )
                },
            )
        }
    }
}