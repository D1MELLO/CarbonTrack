import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ProgressCircle(
    progress: Int, // Valor de progresso (0 a 100)
    size: Dp = 200.dp, // Tamanho do círculo
    strokeWidth: Dp = 10.dp, // Espessura do stroke
    strokeColor: Color = Color(0xFF6ED7B5) // Cor do stroke
) {
    // Calcula o ângulo do arco com base no progresso
    val sweepAngle = (progress * 360) / 100f

    Canvas(
        modifier = Modifier.size(size)
    ) {
        // Desenha o círculo de fundo (opcional)
        drawCircle(
            color = Color.LightGray.copy(alpha = 0.3f),
            style = Stroke(width = strokeWidth.toPx())
        )

        // Desenha o arco progressivo
        drawArc(
            color = strokeColor,
            startAngle = -90f, // Começa no topo (-90 graus)
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(
                width = strokeWidth.toPx(),
                cap = StrokeCap.Round // Borda arredondada
            )
        )
    }
}