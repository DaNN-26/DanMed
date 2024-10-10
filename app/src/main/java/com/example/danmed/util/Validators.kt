package com.example.danmed.util

import android.util.Patterns

class EmailValidator {
    operator fun invoke(email: String): EmailValidationResult =
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches())
            EmailValidationResult.CORRECT
        else
            EmailValidationResult.INCORRECT_FORMAT
}
class PasswordValidator {
    operator fun invoke(password: String): PasswordValidationResult =
        if(password.length < 8)
            PasswordValidationResult.NOT_LONG_ENOUGH
        else if(!password.contains("[0-9]".toRegex()))
            PasswordValidationResult.NOT_ENOUGH_DIGITS
        else
            PasswordValidationResult.CORRECT
}

enum class EmailValidationResult {
    INCORRECT_FORMAT,
    CORRECT
}
enum class PasswordValidationResult {
    NOT_LONG_ENOUGH,
    NOT_ENOUGH_DIGITS,
    CORRECT
}