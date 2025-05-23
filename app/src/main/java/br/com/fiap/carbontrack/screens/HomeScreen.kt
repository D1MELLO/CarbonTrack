package br.com.fiap.carbontrack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.carbontrack.R
import br.com.fiap.carbontrack.components.TopFrame

@Composable
fun HomeScreen(
    onNavigateToCalculator: () -> Unit,
    onNavigateToChallenges: () -> Unit,
    onNavigateToProgress: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToTips: () -> Unit
) {
    // Lista de cards da Home
    val homeCards = listOf(
        HomeCard(R.drawable.calculator, "Calculadora", onNavigateToCalculator),
        HomeCard(R.drawable.bulb, "Dicas", onNavigateToTips),
        HomeCard(R.drawable.target, "Desafios", onNavigateToChallenges),
        HomeCard(R.drawable.progress, "Progresso", onNavigateToProgress)
    )

    // Layout da página
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        TopFrame(
            onCalculadoraClick = onNavigateToCalculator,
            onDesafiosClick = onNavigateToChallenges,
            onProgressoClick = onNavigateToProgress,
            onHomeClick = onNavigateToHome,
            onTipsClick = onNavigateToTips
        )

        // Título da Home
        Text(
            text = "Home",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){



        // Grade de cards (2 colunas)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(homeCards) { card ->
                HomeCardItem(card)
            }
        }
    }}
}

// Data class para representar um card da Home
data class HomeCard(
    val iconResId: Int,
    val title: String,
    val onClick: () -> Unit
)

@Composable
fun HomeCardItem(card: HomeCard) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF76E1BD), Color(0xFF034F49))
                ),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable { card.onClick() } // Navegação ao clicar no card
            .padding(16.dp),
        contentAlignment = Alignment.Center // Centraliza o conteúdo do Box
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), // Ocupa toda a largura do Box
            horizontalAlignment = Alignment.CenterHorizontally, // Centraliza horizontalmente
            verticalArrangement = Arrangement.Center // Centraliza verticalmente
        ) {
            // Ícone ao centro
            Image(
                painter = painterResource(id = card.iconResId),
                contentDescription = card.title,
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Texto embaixo
            Text(
                text = card.title,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally) // Garante que o texto esteja centralizado
            )
        }
    }
}