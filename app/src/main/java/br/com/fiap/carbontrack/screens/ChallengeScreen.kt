package br.com.fiap.carbontrack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.carbontrack.components.TopFrame
import br.com.fiap.carbontrack.R
import androidx.compose.foundation.background

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
                DesafioItem(desafio = desafio, iconResId = iconResId)
            }
        }
    }
}

@Composable
fun DesafioItem(desafio: String, iconResId: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), // Espaçamento entre os itens
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Camadas de fundo e ícone
        Box(
            modifier = Modifier.size(95.dp), // Tamanho da elipse externa
            contentAlignment = Alignment.Center
        ) {
            // Primeira camada: Elipse verde
            Box(
                modifier = Modifier
                    .size(95.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF23BCA3)) // Cor da elipse
            )

            // Segunda camada: Círculo azul escuro com borda branca
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF0B2D3B)) // Cor do círculo
                    .padding(1.dp) // Borda branca
            )

            // Ícone centralizado
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = "Ícone do desafio",
                modifier = Modifier.size(48.dp) // Tamanho do ícone
            )
        }

        // Nome do desafio
        Text(
            text = desafio,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

        // Status do desafio
        Text(
            text = "concluir",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}