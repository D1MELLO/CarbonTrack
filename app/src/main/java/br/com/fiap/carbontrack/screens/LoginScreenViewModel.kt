package br.com.fiap.carbontrack.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.carbontrack.database.AppDatabase
import kotlinx.coroutines.launch

class LoginScreenViewModel(private val database: AppDatabase) : ViewModel() {

    // Estados para os campos de entrada
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    // Estados para mensagens de erro
    var emailError by mutableStateOf<String?>(null)
        private set
    var passwordError by mutableStateOf<String?>(null)
        private set

    // Funções para atualizar os campos e validar em tempo real
    fun onEmailChange(newEmail: String) {
        email = newEmail
        emailError = validateEmail(newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
        passwordError = validatePassword(newPassword)
    }

    // Validação de e-mail
    private fun validateEmail(email: String): String? {
        return when {
            email.isBlank() -> "E-mail é obrigatório."
            !isValidEmail(email) -> "E-mail inválido. Exemplo: usuario@exemplo.com"
            else -> null
        }
    }

    // Validação de senha
    private fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "Senha é obrigatória."
            password.length < 6 -> "A senha deve ter pelo menos 6 caracteres."
            !isValidPassword(password) -> "A senha deve conter letras, números e símbolos."
            else -> null
        }
    }

    // Verifica se o e-mail é válido
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return email.matches(emailRegex.toRegex())
    }

    // Verifica se a senha é válida
    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\",.<>/?]).{6,}\$"
        return password.matches(passwordRegex.toRegex())
    }

    // Função de login
    fun login(onSuccess: () -> Unit, onError: () -> Unit) {
        // Valida os campos antes de prosseguir
        val emailValidation = validateEmail(email)
        val passwordValidation = validatePassword(password)

        if (emailValidation != null || passwordValidation != null) {
            emailError = emailValidation
            passwordError = passwordValidation
            onError()
            return
        }

        viewModelScope.launch {
            val user = database.userDao().getUser(email, password)
            if (user != null) {
                onSuccess()
            } else {
                emailError = "E-mail ou senha incorretos."
                passwordError = "E-mail ou senha incorretos."
                onError()
            }
        }
    }
}