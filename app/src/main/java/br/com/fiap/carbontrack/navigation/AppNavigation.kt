package br.com.fiap.carbontrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.carbontrack.screens.CalculatorScreen
import br.com.fiap.carbontrack.screens.ChallengeScreen
import br.com.fiap.carbontrack.screens.HomeScreen
import br.com.fiap.carbontrack.screens.ProgressScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home" // Tela inicial
    ) {
        // Rota para a HomeScreen
        composable("home") {
            HomeScreen(
                onNavigateToCalculadora = { navController.navigate("calculadora") },
                onNavigateToDesafios = { navController.navigate("desafios") },
                onNavigateToProgresso = { navController.navigate("progresso") }
            )
        }

        // Rota para a CalculadoraScreen
        composable("calculadora") {
            CalculatorScreen()
        }

        // Rota para a DesafiosScreen
        composable("desafios") {
            ChallengeScreen(
                onNavigateToCalculadora = { navController.navigate("calculadora") },
                onNavigateToDesafios = { navController.navigate("desafios") },
                onNavigateToProgresso = { navController.navigate("progresso") }
            )
        }

        // Rota para a ProgressoScreen
        composable("progresso") {
            ProgressScreen()
        }
    }
}