package br.com.fiap.carbontrack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.carbontrack.R
import br.com.fiap.carbontrack.components.ComponentButton
import br.com.fiap.carbontrack.components.ComponentTextField
import br.com.fiap.carbontrack.database.AppDatabase
import br.com.fiap.carbontrack.factory.CadastroScreenViewModelFactory
import br.com.fiap.carbontrack.ui.theme.Pridi
import androidx.compose.runtime.remember
import androidx.compose.material3.MaterialTheme

@Composable
fun CadastroScreen(
    onCadastroSuccess: () -> Unit,
    onCadastroError: (String) -> Unit
) {
    val context = LocalContext.current
    val database = remember { AppDatabase.getDatabase(context) } // Inicializa o banco de dados
    val viewModel: CadastroScreenViewModel = viewModel(
        factory = CadastroScreenViewModelFactory(database) // Passa o banco de dados para o ViewModel
    )

    val gradientColors = listOf(
        colorResource(R.color.greendarker_system),
        colorResource(R.color.greensoft_system)
    )

    val gradientBrush = Brush.linearGradient(
        colors = gradientColors,
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()), // Adiciona rolagem vertical
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = "CarbonTrack",
                fontSize = 40.sp,
                color = Color.White,
                fontFamily = Pridi,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Orientação para o campo de nome
            Text(
                text = "Use apenas letras e espaços. Exemplo: João Silva",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            // Campo de Nome
            ComponentTextField(
                modifier = Modifier.padding(bottom = 8.dp),
                value = viewModel.nome,
                onValueChange = { viewModel.onNomeChange(it) },
                label = "Nome",
                placeholder = "Digite seu nome",
                keyboardType = KeyboardType.Text,
                isError = viewModel.isNomeError,
                errorMessage = if (viewModel.isNomeError) "Nome inválido. Use apenas letras e espaços." else null,
                textColor = Color.White,
                placeholderColor = Color.White.copy(alpha = 0.7f)
            )

            // Orientação para o campo de e-mail
            Text(
                text = "Use um e-mail válido. Exemplo: joao.silva@email.com",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            // Campo de E-mail
            ComponentTextField(
                modifier = Modifier.padding(bottom = 8.dp),
                value = viewModel.email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = "E-mail",
                placeholder = "Digite seu e-mail",
                keyboardType = KeyboardType.Email,
                isError = viewModel.isEmailError,
                errorMessage = if (viewModel.isEmailError) "E-mail inválido. Use um formato válido." else null,
                textColor = Color.White,
                placeholderColor = Color.White.copy(alpha = 0.7f)
            )

            // Orientação para o campo de senha
            Text(
                text = "Use pelo menos 6 caracteres, incluindo letras maiúsculas, números e símbolos.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            // Campo de Senha
            ComponentTextField(
                modifier = Modifier.padding(bottom = 8.dp),
                value = viewModel.senha,
                onValueChange = { viewModel.onSenhaChange(it) },
                label = "Senha",
                placeholder = "Digite sua senha",
                keyboardType = KeyboardType.Password,
                isPassword = true,
                isError = viewModel.isSenhaError,
                errorMessage = if (viewModel.isSenhaError) "Senha inválida. Use pelo menos 6 caracteres, incluindo letras maiúsculas, números e símbolos." else null,
                textColor = Color.White,
                placeholderColor = Color.White.copy(alpha = 0.7f)
            )

            // Orientação para o campo de confirmar senha
            Text(
                text = "Repita a senha digitada acima.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            // Campo de Confirmar Senha
            ComponentTextField(
                modifier = Modifier.padding(bottom = 8.dp),
                value = viewModel.confirmarSenha,
                onValueChange = { viewModel.onConfirmarSenhaChange(it) },
                label = "Confirmar Senha",
                placeholder = "Confirme sua senha",
                keyboardType = KeyboardType.Password,
                isPassword = true,
                isError = viewModel.isConfirmarSenhaError,
                errorMessage = if (viewModel.isConfirmarSenhaError) "As senhas não coincidem." else null,
                textColor = Color.White,
                placeholderColor = Color.White.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(8.dp))
            ComponentButton(
                onClick = {
                    viewModel.cadastrar(
                        onSuccess = onCadastroSuccess,
                        onError = onCadastroError
                    )
                },
                text = "Cadastrar",
            )
        }
    }
}