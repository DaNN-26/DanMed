package com.example.danmed.shared

import android.content.Context

fun saveAuthState(context: Context, isLoggedIn: Boolean, email: String) {
    val sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putBoolean("isLoggedIn", isLoggedIn)
        putString("email", email)
        apply()
    }
}
fun isUserLoggedIn(context: Context): Boolean {
    val sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    return sharedPref.getBoolean("isLoggedIn", false)
}

fun getEmail(context: Context): String {
    val sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    return sharedPref.getString("email", "") ?: ""
}