package com.example.cinema.presentation.ui.navigation

import com.ramcosta.composedestinations.annotation.NavHostGraph
import com.ramcosta.composedestinations.annotation.parameters.CodeGenVisibility

@NavHostGraph(
    route = "auth_route",
    visibility = CodeGenVisibility.INTERNAL,
)
annotation class AuthGraph