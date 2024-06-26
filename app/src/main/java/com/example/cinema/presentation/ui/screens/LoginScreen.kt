package com.example.cinema.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cinema.common.Constants.ADMIN_EMAIL
import com.example.cinema.common.Constants.CLIENT_EMAIL
import com.example.cinema.common.Constants.PASS
import com.example.cinema.presentation.ui.navigation.AuthGraph
import com.example.cinema.presentation.ui.theme.Purple80
import com.example.cinema.presentation.viewmodel.LoginViewModel
import com.example.cinema.presentation.viewmodel.MoviesViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.generated.destinations.SignUpScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination<AuthGraph>(start = true)
@Composable
fun LoginScreen(navigator: DestinationsNavigator) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val viewModel = hiltViewModel<LoginViewModel>()

    val isUserAuthorized by viewModel.isUserAuthorized.collectAsState()

    if (!isUserAuthorized) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }) { contentPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                ClickableText(
                    text = AnnotatedString("Зарегистрироваться"),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(40.dp)
                        .padding(contentPadding),
                    onClick = {
                        navigator.navigate(SignUpScreenDestination())
                    },
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Default,
                        textDecoration = TextDecoration.Underline,
                        color = Purple80
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val email = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf(TextFieldValue()) }

                Text(
                    text = "Cinema",
                    style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
                )

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "E-mail") },
                    value = email.value,
                    onValueChange = { email.value = it })

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Пароль") },
                    value = password.value,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { password.value = it })

                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            if (password.value.text == PASS
                                && (email.value.text == ADMIN_EMAIL || email.value.text == CLIENT_EMAIL)
                            ) {
                                viewModel.setUserAuthorized(true)
                                viewModel.checkUserAuthorized()
                                viewModel.setEmail(email.value.text)
                            } else {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Неверный логин или пароль")
                                }
                            }
                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Войти")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    } else {
        DestinationsNavHost(navGraph = NavGraphs.root)
    }
}