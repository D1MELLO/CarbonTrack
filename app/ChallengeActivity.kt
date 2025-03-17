package br.com.fiap.carbontrack.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import br.com.fiap.carbontrack.R

class LoginScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreenContent()
        }
    }
}

@Composable
fun ChallengeContent() {
    // Chame o Composable da sua tela de login
    LoginScreen(
        onLoginSuccess = { /* Lógica para sucesso no login */ },
        onLoginError = { /* Lógica para erro no login */ },
        onNavigateToCadastro = { /* Lógica para navegar para cadastro */ }
    )
}