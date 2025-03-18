package br.com.fiap.carbontrack.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.carbontrack.screens.CalculatorScreen
import br.com.fiap.carbontrack.screens.ChallengeScreen
import br.com.fiap.carbontrack.screens.HomeScreen
import br.com.fiap.carbontrack.screens.LoginScreen
import br.com.fiap.carbontrack.screens.CadastroScreen
import br.com.fiap.carbontrack.screens.ProgressScreen
import br.com.fiap.carbontrack.screens.SustainableTips

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // Rota para a LoginScreen
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") }, // Navega para a HomeScreen após o login
                onLoginError = { /* Tratar erro de login */ },
                onNavigateToCadastro = { navController.navigate("cadastro") } // Navega para a tela de cadastro
            )
        }

        // Rota para a CadastroScreen
        composable("cadastro") {
            CadastroScreen(
                onCadastroSuccess = {
                    navController.popBackStack() // Volta para a tela de login após o cadastro
                },
                onCadastroError = { /* Tratar erro de cadastro */ }
            )
        }

        // Rota para a HomeScreen
        composable("home") {
            HomeScreen(
                onNavigateToCalculator = { navController.navigate("calculadora") },
                onNavigateToChallenges = { navController.navigate("desafios") },
                onNavigateToProgress = { navController.navigate("progresso") },
                onNavigateToHome = { navController.navigate("home") },
                onNavigateToTips = { navController.navigate("dicas") }
            )
        }

        // Rota para a CalculadoraScreen
        composable("calculadora") {
            CalculatorScreen(navController)
        }

        // Rota para a DesafiosScreen
        composable("desafios") {
            ChallengeScreen(
                onNavigateToCalculadora = { navController.navigate("calculadora") },
                onNavigateToDesafios = { navController.navigate("desafios") },
                onNavigateToProgresso = { navController.navigate("progresso") },
                onNavigateToHome = { navController.navigate("home") },
                onNavigateToTips = { navController.navigate("dicas") }
            )
        }

        composable("progresso") {
            ProgressScreen(
                onNavigateToCalculadora = { navController.navigate("calculadora") },
                onNavigateToDesafios = { navController.navigate("desafios") },
                onNavigateToProgresso = { navController.navigate("progresso") },
                onNavigateToHome = { navController.navigate("home") },
                onNavigateToTips = { navController.navigate("dicas") }
            )
        }

        composable("dicas") {
            SustainableTips(
                onNavigateToCalculator = { navController.navigate("calculadora") },
                onNavigateToChallenge = { navController.navigate("desafios") },
                onNavigateToProgress = { navController.navigate("progresso") },
                onNavigateToHome = { navController.navigate("home") },
                onNavigateToTips = { navController.navigate("dicas") }
            )
        }


    }
}