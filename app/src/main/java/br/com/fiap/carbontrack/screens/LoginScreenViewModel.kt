package br.com.fiap.carbontrack.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginScreenViewModel: ViewModel() {
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var isEmailError by mutableStateOf(false)
        private set
    var isPasswordError by mutableStateOf(false)
        private set

    fun onEmailChange(newEmail: String) {
        email = newEmail
        isEmailError = !isValidEmail(newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
        isPasswordError = !isValidPassword(newPassword)
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return email.matches(emailRegex.toRegex())
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\",.<>/?]).{6,}\$"
        return password.matches(passwordRegex.toRegex())
    }
}