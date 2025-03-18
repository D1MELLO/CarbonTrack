package br.com.fiap.carbontrack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.carbontrack.R
import br.com.fiap.carbontrack.components.TopFrame

@Composable
fun ProgressScreen(
    onNavigateToCalculadora: () -> Unit,
    onNavigateToDesafios: () -> Unit,
    onNavigateToProgresso: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToTips: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Adiciona o TopFrame com o menu
        TopFrame(
            onCalculadoraClick = onNavigateToCalculadora,
            onDesafiosClick = onNavigateToDesafios,
            onProgressoClick = onNavigateToProgresso,
            onHomeClick = onNavigateToHome,
            onTipsClick = onNavigateToTips
        )

        // Título "Progresso"
        Text(
            text = "Progresso",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        // Coluna com o círculo e o texto "Desafios concluídos" (ADICIONE AQUI)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(110.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(95.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF23BCA3))
                )
                Box(
                    modifier = Modifier
                        .size(75.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(1.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "12",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Desafios concluídos",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        }

// Título "Medalhas"
        Text(
            text = "Medalhas",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        // Lista de medalhas
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Medalha 1: Economizador de água
            MedalhaItem(
                iconResId = R.drawable.medal,
                title = "Economizador de água"
            )

            // Medalha 2: Campeão de reciclagem
            MedalhaItem(
                iconResId = R.drawable.medal,
                title = "Campeão de reciclagem"
            )

            // Medalha 3: Guardião da floresta
            MedalhaItem(
                iconResId = R.drawable.medal,
                title = "Guardião da floresta"
            )
        }
    }
}

@Composable
fun MedalhaItem(iconResId: Int, title: String) {
    Box(
        modifier = Modifier
            .border(
                width = 3.dp,
                color = Color(0xFF0B2D3B),
                shape = RoundedCornerShape(size = 15.dp)
            )
            .width(362.dp)
            .height(75.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Box verde atrás da medalha
            Box(
                modifier = Modifier
                    .width(90.dp)
                    .height(80.dp)
                    .background(
                        color = Color(0xFF6ED7B5),
                        shape = RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 0.dp,
                            bottomStart = 15.dp,
                            bottomEnd = 0.dp
                        )
                    ),

                contentAlignment = Alignment.Center
            ) {
                // Ícone da medalha
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = "Medalha",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp)
                )
            }

            // Texto ao lado direito
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.22.sp,
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                ),
                modifier = Modifier
                    .width(257.dp)
                    .height(24.dp)
                    .padding(start = 16.dp)
            )
        }
    }
}