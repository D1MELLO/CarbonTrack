package br.com.fiap.carbontrack.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.carbontrack.R

@Composable
fun ComponentButton(
    text: String,
    onClick: () -> Unit,
//    modifier: Modifier = Modifier,
    backgroundColor: Color = colorResource(id = R.color.greenbtn_system),
    textColor: Color = Color.White
) {
    Button(
        onClick = onClick, // Corrigido: Chama o onClick passado como parâmetro
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        )
    ) {
        Text(
            text = text,
            fontSize = 24.sp
        )
    }
}