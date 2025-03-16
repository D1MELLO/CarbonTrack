package br.com.fiap.carbontrack.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.fiap.carbontrack.database.AppDatabase
import br.com.fiap.carbontrack.screens.LoginScreenViewModel

class LoginScreenViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST") // Suprime o aviso de unchecked cast
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginScreenViewModel::class.java)) {
            return LoginScreenViewModel(database) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}