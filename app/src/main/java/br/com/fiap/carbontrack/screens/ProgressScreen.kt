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
fun ProgressScreen(
    onNavigateToCalculadora: () -> Unit,
    onNavigateToDesafios: () -> Unit,
    onNavigateToProgresso: () -> Unit
) {

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
            text = "Progresso",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )


    }
}