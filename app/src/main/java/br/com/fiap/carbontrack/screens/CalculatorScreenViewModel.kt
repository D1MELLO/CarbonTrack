package br.com.fiap.carbontrack.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import br.com.fiap.carbontrack.api.CarbonFootprintApi
import br.com.fiap.carbontrack.api.RetrofitClient
import br.com.fiap.carbontrack.model.CarbonFootprintRequest
import br.com.fiap.carbontrack.model.TransportData
import br.com.fiap.carbontrack.model.EnergyData
import br.com.fiap.carbontrack.model.FoodData

class CalculatorScreenViewModel : ViewModel() {
    // Estados para os campos de entrada
    private val _carDistance = MutableStateFlow("")
    val carDistance: StateFlow<String> get() = _carDistance

    private val _busDistance = MutableStateFlow("")
    val busDistance: StateFlow<String> get() = _busDistance

    private val _planeDistance = MutableStateFlow("")
    val planeDistance: StateFlow<String> get() = _planeDistance

    private val _electricityUsage = MutableStateFlow("")
    val electricityUsage: StateFlow<String> get() = _electricityUsage

    private val _meatConsumption = MutableStateFlow("")
    val meatConsumption: StateFlow<String> get() = _meatConsumption

    private val _dairyConsumption = MutableStateFlow("")
    val dairyConsumption: StateFlow<String> get() = _dairyConsumption

    // Estados para mensagens de erro
    private val _carDistanceError = MutableStateFlow<String?>(null)
    val carDistanceError: StateFlow<String?> get() = _carDistanceError

    private val _busDistanceError = MutableStateFlow<String?>(null)
    val busDistanceError: StateFlow<String?> get() = _busDistanceError

    private val _planeDistanceError = MutableStateFlow<String?>(null)
    val planeDistanceError: StateFlow<String?> get() = _planeDistanceError

    private val _electricityUsageError = MutableStateFlow<String?>(null)
    val electricityUsageError: StateFlow<String?> get() = _electricityUsageError

    private val _meatConsumptionError = MutableStateFlow<String?>(null)
    val meatConsumptionError: StateFlow<String?> get() = _meatConsumptionError

    private val _dairyConsumptionError = MutableStateFlow<String?>(null)
    val dairyConsumptionError: StateFlow<String?> get() = _dairyConsumptionError

    // Estado para o resultado do cálculo
    private val _totalFootprint = MutableStateFlow<Double?>(null)
    val totalFootprint: StateFlow<Double?> get() = _totalFootprint

    // Estado para feedback visual (sucesso/erro)
    private val _showSuccessMessage = MutableStateFlow(false)
    val showSuccessMessage: StateFlow<Boolean> get() = _showSuccessMessage

    // Estado para indicar carregamento
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    // Estado para mensagens de erro
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    // Instância da API
    private val carbonFootprintApi: CarbonFootprintApi = RetrofitClient.instance

    // Funções para atualizar os estados
    fun updateCarDistance(value: String) {
        _carDistance.value = value
        validateCarDistance(value)
    }

    fun updateBusDistance(value: String) {
        _busDistance.value = value
        validateBusDistance(value)
    }

    fun updatePlaneDistance(value: String) {
        _planeDistance.value = value
        validatePlaneDistance(value)
    }

    fun updateElectricityUsage(value: String) {
        _electricityUsage.value = value
        validateElectricityUsage(value)
    }

    fun updateMeatConsumption(value: String) {
        _meatConsumption.value = value
        validateMeatConsumption(value)
    }

    fun updateDairyConsumption(value: String) {
        _dairyConsumption.value = value
        validateDairyConsumption(value)
    }

    // Funções de validação
    private fun validateCarDistance(value: String) {
        _carDistanceError.value = when {
            value.isBlank() -> "Campo obrigatório"
            !value.matches(Regex("\\d+(\\.\\d+)?")) -> "Apenas números são permitidos"
            else -> null
        }
    }

    private fun validateBusDistance(value: String) {
        _busDistanceError.value = when {
            value.isBlank() -> "Campo obrigatório"
            !value.matches(Regex("\\d+(\\.\\d+)?")) -> "Apenas números são permitidos"
            else -> null
        }
    }

    private fun validatePlaneDistance(value: String) {
        _planeDistanceError.value = when {
            value.isBlank() -> "Campo obrigatório"
            !value.matches(Regex("\\d+(\\.\\d+)?")) -> "Apenas números são permitidos"
            else -> null
        }
    }

    private fun validateElectricityUsage(value: String) {
        _electricityUsageError.value = when {
            value.isBlank() -> "Campo obrigatório"
            !value.matches(Regex("\\d+(\\.\\d+)?")) -> "Apenas números são permitidos"
            else -> null
        }
    }

    private fun validateMeatConsumption(value: String) {
        _meatConsumptionError.value = when {
            value.isBlank() -> "Campo obrigatório"
            !value.matches(Regex("\\d+(\\.\\d+)?")) -> "Apenas números são permitidos"
            else -> null
        }
    }

    private fun validateDairyConsumption(value: String) {
        _dairyConsumptionError.value = when {
            value.isBlank() -> "Campo obrigatório"
            !value.matches(Regex("\\d+(\\.\\d+)?")) -> "Apenas números são permitidos"
            else -> null
        }
    }

    // Função para validar todos os campos
    private fun validateFields(): Boolean {
        validateCarDistance(_carDistance.value)
        validateBusDistance(_busDistance.value)
        validatePlaneDistance(_planeDistance.value)
        validateElectricityUsage(_electricityUsage.value)
        validateMeatConsumption(_meatConsumption.value)
        validateDairyConsumption(_dairyConsumption.value)

        return _carDistanceError.value == null &&
                _busDistanceError.value == null &&
                _planeDistanceError.value == null &&
                _electricityUsageError.value == null &&
                _meatConsumptionError.value == null &&
                _dairyConsumptionError.value == null
    }

    fun calculateCarbonFootprint(onSuccess: (Double) -> Unit, onError: (String) -> Unit) {
        if (validateFields()) {
            viewModelScope.launch {
                _isLoading.value = true
                _errorMessage.value = null

                try {
                    // Cria a requisição para a API
                    val request = CarbonFootprintRequest(
                        transport = TransportData(
                            car_distance = _carDistance.value.toDoubleOrNull() ?: 0.0,
                            bus_distance = _busDistance.value.toDoubleOrNull() ?: 0.0,
                            plane_distance = _planeDistance.value.toDoubleOrNull() ?: 0.0
                        ),
                        energy = EnergyData(
                            electricity_usage = _electricityUsage.value.toDoubleOrNull() ?: 0.0
                        ),
                        food = FoodData(
                            meat_consumption = _meatConsumption.value.toDoubleOrNull() ?: 0.0,
                            dairy_consumption = _dairyConsumption.value.toDoubleOrNull() ?: 0.0
                        )
                    )

                    // Log da requisição
                    println("Requisição enviada: $request")

                    // Faz a requisição à API
                    val response = carbonFootprintApi.calculateFootprint(request)

                    // Log da resposta
                    println("Resposta da API: $response")

                    // Atualiza o estado com o resultado
                    _totalFootprint.value = response.total_footprint
                    _showSuccessMessage.value = true

                    // Chama o callback de sucesso
                    onSuccess(response.total_footprint)
                } catch (e: Exception) {
                    // Log do erro
                    println("Erro na requisição: ${e.message}")

                    // Trata erros
                    _errorMessage.value = "Erro ao calcular a pegada de carbono: ${e.message}"
                    onError(e.message ?: "Erro desconhecido")
                } finally {
                    _isLoading.value = false
                }
            }
        } else {
            _errorMessage.value = "Preencha todos os campos corretamente."
            onError("Preencha todos os campos corretamente.")
        }
    }

    // Função para resetar o feedback de sucesso
    fun resetSuccessMessage() {
        _showSuccessMessage.value = false
    }

    // Função para resetar mensagens de erro
    fun resetErrorMessage() {
        _errorMessage.value = null
    }

    fun setErrorMessage() {
        TODO("Not yet implemented")
    }
}