package com.example.danmed.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Sign(
    email: String,
    pass: String,
    onEmailChange: (String) -> Unit,
    onPassChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    buttonText: String,
    textButtonText: String,
    onTextButtonClick: () -> Unit,
    emailSupportingText: String,
    passwordSupportingText: String,
    isError: Boolean,
    contentPadding: PaddingValues
) {
    var passVisible by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp)
            .padding(contentPadding)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { onEmailChange(it) },
            label = { Text(text = "Email") },
            supportingText = {
                Text(text = emailSupportingText)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            isError = isError
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = pass,
            onValueChange = { onPassChange(it) },
            label = { Text(text = "Пароль") },
            supportingText = {
                Text(text = passwordSupportingText)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            isError = isError,
            visualTransformation = if(passVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if(passVisible) Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                IconButton(onClick = {
                    passVisible = !passVisible
                }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = onButtonClick,
            enabled = !(email == "" || pass == "")
        ) {
            Text(
                text = buttonText,
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
        TextButton(onClick = onTextButtonClick) {
            Text(
                text = textButtonText,
                fontSize = 16.sp
            )
        }
    }
}