package com.example.danmed.ui.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    isStartScreen: Boolean = false,
    isAvailableItemsClicked: Boolean = false,
    onAccountIconClick: () -> Unit = {},
    onAvailableItemsClick: () -> Unit = {},
    navigateBack: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            }
            if(isStartScreen) {
                Row {
                    IconButton(onClick = onAccountIconClick) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = onAvailableItemsClick) {
                        Icon(
                            imageVector = if(!isAvailableItemsClicked)
                                Icons.Default.CheckCircleOutline
                            else
                                Icons.Filled.CheckCircle,
                            contentDescription = null
                        )
                    }
                }
            }
        },
        modifier = Modifier.shadow(12.dp)
    )
}