package br.com.fiap.carbontrack.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.carbontrack.database.AppDatabase
import br.com.fiap.carbontrack.model.User
import kotlinx.coroutines.launch

class CadastroScreenViewModel(private val database: AppDatabase) : ViewModel() {

    var nome by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var senha by mutableStateOf("")
        private set
    var confirmarSenha by mutableStateOf("")
        private set
    var isNomeError by mutableStateOf(false)
        private set
    var isEmailError by mutableStateOf(false)
        private set
    var isSenhaError by mutableStateOf(false)
        private set
    var isConfirmarSenhaError by mutableStateOf(false)
        private set

    fun onNomeChange(newNome: String) {
        nome = newNome
        isNomeError = newNome.isBlank()
    }

    fun onEmailChange(newEmail: String) {
        email = newEmail
        isEmailError = !isValidEmail(newEmail)
    }

    fun onSenhaChange(newSenha: String) {
        senha = newSenha
        isSenhaError = !isValidPassword(newSenha)
    }

    fun onConfirmarSenhaChange(newConfirmarSenha: String) {
        confirmarSenha = newConfirmarSenha
        isConfirmarSenhaError = newConfirmarSenha != senha
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return email.matches(emailRegex.toRegex())
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\",.<>/?]).{6,}\$"
        return password.matches(passwordRegex.toRegex())
    }

    fun cadastrar(onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (isNomeError || isEmailError || isSenhaError || isConfirmarSenhaError) {
            onError("Preencha todos os campos corretamente.")
            return
        }

        viewModelScope.launch {
            val existingUser = database.userDao().getUserByEmail(email)
            if (existingUser == null) {
                val user = User(
                    nome = nome,
                    email = email,
                    password = senha
                )
                database.userDao().insert(user)
                onSuccess()
            } else {
                onError("E-mail já cadastrado.")
            }
        }
    }
}