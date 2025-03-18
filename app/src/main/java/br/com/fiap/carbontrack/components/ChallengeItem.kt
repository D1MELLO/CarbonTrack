package br.com.fiap.carbontrack.components

import ConcluirButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.carbontrack.R

@Composable
fun ChallengeItem(
    desafio: String, // Nome do desafio
    iconResId: Int, // Ícone do desafio
    onConcluirClick: () -> Unit // Ação ao clicar no botão "Concluir"
) {
    // Estado para controlar se o botão foi clicado
    val isClicked = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), // Espaçamento entre os itens
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Camadas de fundo e ícone
        Box(
            modifier = Modifier.size(110.dp), // Tamanho da elipse externa
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

        // Botão "Concluir"
        ConcluirButton(
            onClick = {
                isClicked.value = !isClicked.value // Alterna o estado ao clicar
                onConcluirClick() // Executa a ação passada como parâmetro
            },
            isClicked = isClicked.value
        )
    }
}