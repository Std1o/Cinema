package com.example.cinema.presentation.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinema.R
import com.example.cinema.presentation.ui.theme.Pink40

@Composable
fun SearchAppBar(actions: @Composable RowScope.() -> Unit = {}, onQueryChanged: (String) -> Unit) {
    var query: String by rememberSaveable { mutableStateOf("") }
    var collapsed by rememberSaveable { mutableStateOf(true) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    if (collapsed) {
        TopAppBar(
            backgroundColor = Pink40,
            title = {
                Text(
                    text = stringResource(id = R.string.hint_search_query),
                    fontSize = 16.sp
                )
            },
            actions = {
                IconButton(onClick = { collapsed = false }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon"
                    )
                }
                actions()
            },
            modifier = Modifier.shadow(elevation = 5.dp)
        )
    } else {
        TextField(
            value = query,
            onValueChange = { newQuery ->
                query = newQuery
                onQueryChanged(newQuery)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (query.isEmpty()) collapsed = true
                    else {
                        query = ""
                        onQueryChanged("")
                    }
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = "Clear Icon"
                    )
                }
            },
            maxLines = 1,
            placeholder = { Text(text = stringResource(R.string.hint_search_query)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )
    }

    LaunchedEffect(collapsed) {
        if (!collapsed) {
            keyboardController?.show()
            focusRequester.requestFocus()
        }
    }
}