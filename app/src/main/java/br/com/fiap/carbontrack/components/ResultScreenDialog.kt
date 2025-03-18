package br.com.fiap.carbontrack.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.com.fiap.carbontrack.R

@Composable
fun ResultScreenDialog(
    totalFootprint: Double,
    onDismiss: () -> Unit // Função para fechar o diálogo
) {
    val cardColor = colorResource(id = R.color.greendarker_system) // Cor do card (verde escuro)

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true, // Fecha ao pressionar o botão de voltar
            dismissOnClickOutside = true // Fecha ao clicar fora do diálogo
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            color = cardColor // Usa a cor do card
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título
                Text(
                    text = "Resultado da Pegada de Carbono",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White, // Texto branco
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Valor da pegada de carbono
                Text(
                    text = "${"%.2f".format(totalFootprint)} kg de CO2",
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.White, // Texto branco
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Dicas para reduzir o impacto
                Text(
                    text = "Dicas para reduzir seu impacto:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = Color.White, // Texto branco
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "- Use transporte público 3 vezes por semana.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    color = Color.White, // Texto branco
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "- Reduza o consumo de carne.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    color = Color.White, // Texto branco
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "- Desligue aparelhos eletrônicos quando não estiverem em uso.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    color = Color.White, // Texto branco
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Botão para fechar o diálogo
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White, // Fundo branco
                        contentColor = cardColor // Texto na cor do card
                    )
                ) {
                    Text(text = "Fechar")
                }
            }
        }
    }
}