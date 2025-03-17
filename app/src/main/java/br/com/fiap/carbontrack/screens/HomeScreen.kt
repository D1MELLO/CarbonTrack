package br.com.fiap.carbontrack.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.carbontrack.components.TopFrame

@Composable
fun HomeScreen(
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

        // Conteúdo da HomeScreen
        Text(
            text = "Bem-vindo à HomeScreen!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}