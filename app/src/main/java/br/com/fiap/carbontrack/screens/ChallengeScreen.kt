package br.com.fiap.carbontrack.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.carbontrack.components.TopFrame
import br.com.fiap.carbontrack.R
import androidx.compose.foundation.lazy.grid.items
import br.com.fiap.carbontrack.components.ChallengeItem

@Composable
fun ChallengeScreen(
    onNavigateToCalculadora: () -> Unit,
    onNavigateToDesafios: () -> Unit,
    onNavigateToProgresso: () -> Unit
) {
    // Lista de desafios sustentáveis
    val desafios = listOf(
        "Dia sem carro" to R.drawable.ic_car, // Substitua pelo ícone correto
        "Luzes apagadas" to R.drawable.ic_light, // Substitua pelo ícone correto
        "Comida sustentável" to R.drawable.ic_food, // Substitua pelo ícone correto
        "Reciclagem fácil" to R.drawable.ic_recycle, // Substitua pelo ícone correto
        "Dia sem plástico" to R.drawable.ic_plastic, // Substitua pelo ícone correto
        "Banho rápido" to R.drawable.ic_shower // Substitua pelo ícone correto
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Adiciona o TopFrame com o menu
        TopFrame(
            onCalculadoraClick = onNavigateToCalculadora,
            onDesafiosClick = onNavigateToDesafios,
            onProgressoClick = onNavigateToProgresso
        )

        // Título da seção de desafios
        Text(
            text = "Desafios sustentáveis",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        // Lista de desafios em duas colunas
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Define duas colunas
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(desafios) { (desafio, iconResId) ->
                ChallengeItem(
                    desafio = desafio,
                    iconResId = iconResId,
                    onConcluirClick = {
                        // Lógica para concluir o desafio
                        println("Desafio concluído: $desafio")
                    }
                )
            }
        }
    }
}