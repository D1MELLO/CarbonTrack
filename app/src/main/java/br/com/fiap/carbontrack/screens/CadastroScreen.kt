package br.com.fiap.carbontrack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
            modifier = Modifier.fillMaxSize(),
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
            ComponentTextField(
                value = viewModel.nome,
                onValueChange = { viewModel.onNomeChange(it) },
                label = "Nome",
                placeholder = "Digite seu nome",
                keyboardType = KeyboardType.Text,
                isError = viewModel.isNomeError,
                errorMessage = if (viewModel.isNomeError) "Nome inválido" else null,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ComponentTextField(
                value = viewModel.email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = "E-mail",
                placeholder = "Digite seu e-mail",
                keyboardType = KeyboardType.Email,
                isError = viewModel.isEmailError,
                errorMessage = if (viewModel.isEmailError) "E-mail inválido" else null,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ComponentTextField(
                value = viewModel.senha,
                onValueChange = { viewModel.onSenhaChange(it) },
                label = "Senha",
                placeholder = "Digite sua senha",
                keyboardType = KeyboardType.Password,
                isPassword = true,
                isError = viewModel.isSenhaError,
                errorMessage = if (viewModel.isSenhaError) "Senha inválida" else null,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ComponentTextField(
                value = viewModel.confirmarSenha,
                onValueChange = { viewModel.onConfirmarSenhaChange(it) },
                label = "Confirmar Senha",
                placeholder = "Confirme sua senha",
                keyboardType = KeyboardType.Password,
                isPassword = true,
                isError = viewModel.isConfirmarSenhaError,
                errorMessage = if (viewModel.isConfirmarSenhaError) "Senhas não coincidem" else null,
                modifier = Modifier.padding(bottom = 8.dp)
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