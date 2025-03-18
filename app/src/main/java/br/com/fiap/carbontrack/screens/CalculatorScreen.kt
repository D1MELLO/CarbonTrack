package br.com.fiap.carbontrack.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.fiap.carbontrack.components.ComponentButton
import br.com.fiap.carbontrack.components.ComponentTextField
import br.com.fiap.carbontrack.components.TopFrame
import br.com.fiap.carbontrack.R
import br.com.fiap.carbontrack.components.ResultScreenDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CalculatorScreen(navController: NavController) {
    val viewModel: CalculatorScreenViewModel = viewModel()
    val carDistance by viewModel.carDistance.collectAsState()
    val busDistance by viewModel.busDistance.collectAsState()
    val planeDistance by viewModel.planeDistance.collectAsState()
    val electricityUsage by viewModel.electricityUsage.collectAsState()
    val meatConsumption by viewModel.meatConsumption.collectAsState()
    val dairyConsumption by viewModel.dairyConsumption.collectAsState()

    val carDistanceError by viewModel.carDistanceError.collectAsState()
    val busDistanceError by viewModel.busDistanceError.collectAsState()
    val planeDistanceError by viewModel.planeDistanceError.collectAsState()
    val electricityUsageError by viewModel.electricityUsageError.collectAsState()
    val meatConsumptionError by viewModel.meatConsumptionError.collectAsState()
    val dairyConsumptionError by viewModel.dairyConsumptionError.collectAsState()

    val showSuccessMessage by viewModel.showSuccessMessage.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    // Estado para controlar a exibição do diálogo de resultado
    var showResultDialog by remember { mutableStateOf(false) }

    // Estado para armazenar o resultado da pegada de carbono
    var totalFootprint by remember { mutableStateOf(0.0) }

    // Scroll state para permitir rolagem
    val scrollState = rememberScrollState()

    // Cor dos cards (verde escuro do gradiente)
    val cardColor = colorResource(id = R.color.greendarker_system)

    // CoroutineScope para chamadas assíncronas
    val coroutineScope = rememberCoroutineScope()

    // Feedback visual de sucesso
    if (showSuccessMessage) {
        LaunchedEffect(Unit) {
            delay(3000) // Exibe a mensagem por 3 segundos
            viewModel.resetSuccessMessage()
        }
        AlertDialog(
            onDismissRequest = { viewModel.resetSuccessMessage() },
            title = { Text("Sucesso!") },
            text = { Text("Cálculo realizado com sucesso.") },
            confirmButton = {
                Button(onClick = { viewModel.resetSuccessMessage() }) {
                    Text("OK")
                }
            }
        )
    }

    // Feedback visual de erro
    if (errorMessage != null) {
        AlertDialog(
            onDismissRequest = { viewModel.resetErrorMessage() },
            title = { Text("Erro") },
            text = { Text(errorMessage!!) },
            confirmButton = {
                Button(onClick = { viewModel.resetErrorMessage() }) {
                    Text("OK")
                }
            }
        )
    }

    // Indicador de carregamento
    if (isLoading) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Calculando...") },
            text = { CircularProgressIndicator() },
            confirmButton = { }
        )
    }

    // Exibe o diálogo de resultado se showResultDialog for true
    if (showResultDialog) {
        ResultScreenDialog(
            totalFootprint = totalFootprint,
            onDismiss = { showResultDialog = false } // Fecha o diálogo
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Adiciona o TopFrame (menu superior)
        TopFrame(
            onCalculadoraClick = { /* Navega para a tela de calculadora (já estamos aqui) */ },
            onDesafiosClick = { navController.navigate("desafios") },
            onProgressoClick = { navController.navigate("progresso") },
            onHomeClick = { navController.navigate("home") },
            onTipsClick = { navController.navigate("dicas") }
        )

        // Conteúdo da calculadora com rolagem
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState) // Habilita a rolagem vertical
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Título
            Text(
                text = "Calculadora de Pegada de Carbono",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Black,
                fontSize = 24.sp,
                color = cardColor // Usa a cor do card para o título
            )

            // Seção de Transporte
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardColor) // Cor do card
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Transporte",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White // Texto branco para contraste
                    )
                    ComponentTextField(
                        value = carDistance,
                        onValueChange = { viewModel.updateCarDistance(it) },
                        label = "Quilômetros de carro",
                        placeholder = "Digite a distância",
                        keyboardType = KeyboardType.Number,
                        textColor = Color.White, // Texto branco
                        placeholderColor = Color.White.copy(alpha = 0.7f) // Placeholder semi-transparente
                    )
                    if (carDistanceError != null) {
                        Text(
                            text = carDistanceError!!,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    ComponentTextField(
                        value = busDistance,
                        onValueChange = { viewModel.updateBusDistance(it) },
                        label = "Quilômetros de ônibus",
                        placeholder = "Digite a distância",
                        keyboardType = KeyboardType.Number,
                        textColor = Color.White,
                        placeholderColor = Color.White.copy(alpha = 0.7f)
                    )
                    if (busDistanceError != null) {
                        Text(
                            text = busDistanceError!!,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    ComponentTextField(
                        value = planeDistance,
                        onValueChange = { viewModel.updatePlaneDistance(it) },
                        label = "Quilômetros de avião",
                        placeholder = "Digite a distância",
                        keyboardType = KeyboardType.Number,
                        textColor = Color.White,
                        placeholderColor = Color.White.copy(alpha = 0.7f)
                    )
                    if (planeDistanceError != null) {
                        Text(
                            text = planeDistanceError!!,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            // Seção de Energia
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardColor)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Energia",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    ComponentTextField(
                        value = electricityUsage,
                        onValueChange = { viewModel.updateElectricityUsage(it) },
                        label = "Consumo de energia (kWh)",
                        placeholder = "Digite o consumo",
                        keyboardType = KeyboardType.Number,
                        textColor = Color.White,
                        placeholderColor = Color.White.copy(alpha = 0.7f)
                    )
                    if (electricityUsageError != null) {
                        Text(
                            text = electricityUsageError!!,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            // Seção de Alimentação
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardColor)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Alimentação",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    ComponentTextField(
                        value = meatConsumption,
                        onValueChange = { viewModel.updateMeatConsumption(it) },
                        label = "Consumo de carne (kg)",
                        placeholder = "Digite o consumo",
                        keyboardType = KeyboardType.Number,
                        textColor = Color.White,
                        placeholderColor = Color.White.copy(alpha = 0.7f)
                    )
                    if (meatConsumptionError != null) {
                        Text(
                            text = meatConsumptionError!!,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    ComponentTextField(
                        value = dairyConsumption,
                        onValueChange = { viewModel.updateDairyConsumption(it) },
                        label = "Consumo de laticínios (kg)",
                        placeholder = "Digite o consumo",
                        keyboardType = KeyboardType.Number,
                        textColor = Color.White,
                        placeholderColor = Color.White.copy(alpha = 0.7f)
                    )
                    if (dairyConsumptionError != null) {
                        Text(
                            text = dairyConsumptionError!!,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            // Botão de calcular
            Spacer(modifier = Modifier.height(16.dp))
            ComponentButton(
                onClick = {
                    coroutineScope.launch {
                        viewModel.calculateCarbonFootprint(
                            onSuccess = { footprint ->
                                totalFootprint = footprint // Armazena o resultado
                                showResultDialog = true // Exibe o diálogo
                            },
                            onError = { error ->
                                viewModel.setErrorMessage()
                            }
                        )
                    }
                },
                text = "Calcular",
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = cardColor, // Cor do botão igual aos cards
                textColor = Color.White // Texto branco
            )
        }
    }
}