package br.com.fiap.carbontrack.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConcluirButton(
    onClick: () -> Unit, // Ação ao clicar no botão
    isClicked: Boolean // Estado do botão (clicado ou não)
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(141.dp) // Largura do botão
            .height(30.dp), // Altura do botão
        shape = RoundedCornerShape(5.dp), // Borda arredondada
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isClicked) Color.White else Color(0xFF034F49), // Fundo
            contentColor = if (isClicked) Color(0xFF034F49) else Color.White // Cor do texto
        ),
        border = if (isClicked) BorderStroke(1.dp, Color(0xFF034F49)) else null // Borda ao clicar
    ) {
        Text(
            text = "Concluir",
            fontSize = 20.sp, // Tamanho da fonte
            fontWeight = FontWeight.Bold, // Peso da fonte
            letterSpacing = 1.2.sp, // Espaçamento entre letras
            lineHeight = 24.217.sp // Altura da linha
        )
    }
}