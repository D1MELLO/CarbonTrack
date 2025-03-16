package br.com.fiap.carbontrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.carbontrack.screens.HomeScreen
import br.com.fiap.carbontrack.screens.CadastroScreen
import br.com.fiap.carbontrack.screens.LoginScreen
import br.com.fiap.carbontrack.ui.theme.CarbonTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarbonTrackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Configuração do NavController
                    val navController = rememberNavController()

                    // Definição das rotas
                    NavHost(navController = navController, startDestination = "login") {
                        // Tela de Login
                        composable("login") {
                            LoginScreen(
                                onLoginSuccess = {
                                    // Navegar para a tela principal após o login bem-sucedido
                                    navController.navigate("home") {
                                        // Limpa o back stack para evitar voltar para a tela de login
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onLoginError = {
                                    // Exibir mensagem de erro (opcional)
                                    // Toast.makeText(this@MainActivity, "E-mail ou senha inválidos", Toast.LENGTH_SHORT).show()
                                },
                                onNavigateToCadastro = {
                                    // Navegar para a tela de cadastro
                                    navController.navigate("cadastro")
                                }
                            )
                        }

                        // Tela de Cadastro
                        composable("cadastro") {
                            CadastroScreen(
                                onCadastroSuccess = {
                                    // Voltar para a tela de login após o cadastro bem-sucedido
                                    navController.navigate("login") {
                                        // Limpa o back stack para evitar voltar para a tela de cadastro
                                        popUpTo("cadastro") { inclusive = true }
                                    }
                                },
                                onCadastroError = { errorMessage ->
                                    // Exibir mensagem de erro (opcional)
                                    // Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
                                }
                            )
                        }

                        // Tela Principal (Home)
                        composable("home") {
                            HomeScreen()
                        }
                    }
                }
            }
        }
    }
}