package br.com.fiap.carbontrack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun SustainableTips(
    onNavigateToCalculator: () -> Unit,
    onNavigateToChallenge: () -> Unit,
    onNavigateToProgress: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToTips: () -> Unit
) {
    // Lista de cards sustentáveis
    val tips = listOf(
        Tip(R.drawable.tree, "Plante uma árvore"),
        Tip(R.drawable.ic_recycle, "Separe o lixo reciclável"),
        Tip(R.drawable.ic_shower, "Economize água no banho"),
        Tip(R.drawable.fuel, "Use transporte sustentável"),
        Tip(R.drawable.burguer, "Reduza o consumo de carne"),
        Tip(R.drawable.ic_recycle, "Use sacolas reutilizáveis")
    )

    // Layout da página
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopFrame(
            onCalculadoraClick = onNavigateToCalculator,
            onDesafiosClick = onNavigateToChallenge,
            onProgressoClick = onNavigateToProgress,
            onHomeClick = onNavigateToHome,
            onTipsClick = onNavigateToTips
        )

        Text(
            text = "Dicas Sustentáveis",
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



        // Lista de cards
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(tips) { tip ->
                SustainableTipCard(tip)
            }
        }
        }
    }
}

// Data class para representar um card
data class Tip(
    val iconResId: Int,
    val title: String
)

@Composable
fun SustainableTipCard(tip: Tip) {
    // Estado para controlar se a estrela está preenchida
    val isStarFilled = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF76E1BD), Color(0xFF034F49))
                ),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(16.dp)
    ) {
        // Conteúdo do card
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícone à esquerda
            Image(
                painter = painterResource(id = tip.iconResId),
                contentDescription = tip.title,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Texto à direita
            Text(
                text = tip.title,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // Estrela no canto superior direito
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable { isStarFilled.value = !isStarFilled.value }
        ) {
            Icon(
                painter = painterResource(
                    id = if (isStarFilled.value) R.drawable.ic_star_filled else R.drawable.ic_star_outlined
                ),
                contentDescription = "Favorito",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}