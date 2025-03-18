package br.com.fiap.carbontrack.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.carbontrack.R
import br.com.fiap.carbontrack.ui.theme.Pridi // Importe a fonte Pridi

@Composable
fun TopFrame(
    onCalculadoraClick: () -> Unit,
    onDesafiosClick: () -> Unit,
    onProgressoClick: () -> Unit,
    onHomeClick: () -> Unit,
    onTipsClick: () -> Unit
) {
    // Estado para controlar a visibilidade do menu
    val menuExpanded = remember { mutableStateOf(false) }

    // Cores do gradiente
    val gradientColors = listOf(
        colorResource(id = R.color.greendarker_system),
        colorResource(id = R.color.greensoft_system)
    )

    // Brush para o gradiente
    val gradientBrush = Brush.linearGradient(
        colors = gradientColors,
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(gradientBrush),
        contentAlignment = Alignment.Center
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Adiciona um padding horizontal
        ) {
            // Ícone do menu
            Icon(
                imageVector = Icons.Default.Menu, // Ícone de três linhas
                contentDescription = "Menu",
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { menuExpanded.value = true } // Abre o menu ao clicar
            )

            // Texto "CarbonTrack"
            Text(
                text = "CarbonTrack",
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = Pridi,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 5.dp)
                    .clickable { onHomeClick() }
            )

            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo CarbonTrack",
                modifier = Modifier.size(40.dp),

            )
        }

        // DropdownMenu com as opções
        DropdownMenu(
            expanded = menuExpanded.value,
            onDismissRequest = { menuExpanded.value = false }, // Fecha o menu ao clicar fora
            modifier = Modifier.background(Color.White) // Fundo branco para o menu
        ) {
            // Opção "Calculadora"
            DropdownMenuItem(
                text = { Text("Calculadora") },
                onClick = {
                    menuExpanded.value = false // Fecha o menu
                    onCalculadoraClick() // Executa a ação
                }
            )

            // Opção "Desafios"
            DropdownMenuItem(
                text = { Text("Desafios") },
                onClick = {
                    menuExpanded.value = false // Fecha o menu
                    onDesafiosClick() // Executa a ação
                }
            )

            // Opção "Progresso"
            DropdownMenuItem(
                text = { Text("Progresso") },
                onClick = {
                    menuExpanded.value = false // Fecha o menu
                    onProgressoClick() // Executa a ação
                }
            )

            DropdownMenuItem(
                text = { Text("Dicas Sustentáveis") },
                onClick = {
                    menuExpanded.value = false // Fecha o menu
                    onTipsClick()
                }
            )
        }
    }
}