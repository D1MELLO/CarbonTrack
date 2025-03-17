package br.com.fiap.carbontrack.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

    // Função para calcular a pegada de carbono
    fun calculateCarbonFootprint() {
        if (validateFields()) {
            viewModelScope.launch {
                val carDistanceValue = _carDistance.value.toDoubleOrNull() ?: 0.0
                val busDistanceValue = _busDistance.value.toDoubleOrNull() ?: 0.0
                val planeDistanceValue = _planeDistance.value.toDoubleOrNull() ?: 0.0
                val electricityUsageValue = _electricityUsage.value.toDoubleOrNull() ?: 0.0
                val meatConsumptionValue = _meatConsumption.value.toDoubleOrNull() ?: 0.0
                val dairyConsumptionValue = _dairyConsumption.value.toDoubleOrNull() ?: 0.0

                val total = calculateTotalFootprint(
                    carDistanceValue,
                    busDistanceValue,
                    planeDistanceValue,
                    electricityUsageValue,
                    meatConsumptionValue,
                    dairyConsumptionValue
                )

                _totalFootprint.value = total
                _showSuccessMessage.value = true
            }
        } else {
            _showSuccessMessage.value = false
        }
    }

    // Função para calcular a pegada de carbono total
    private fun calculateTotalFootprint(
        carDistance: Double,
        busDistance: Double,
        planeDistance: Double,
        electricityUsage: Double,
        meatConsumption: Double,
        dairyConsumption: Double
    ): Double {
        // Fatores de emissão de CO2 (valores aproximados)
        val carEmissionFactor = 0.12 // kg CO2 por km (carro a gasolina)
        val busEmissionFactor = 0.05 // kg CO2 por km (ônibus)
        val planeEmissionFactor = 0.25 // kg CO2 por km (avião)
        val electricityEmissionFactor = 0.5 // kg CO2 por kWh
        val meatEmissionFactor = 27.0 // kg CO2 por kg de carne
        val dairyEmissionFactor = 2.0 // kg CO2 por kg de laticínios

        // Cálculos
        val transportFootprint = (carDistance * carEmissionFactor) +
                (busDistance * busEmissionFactor) +
                (planeDistance * planeEmissionFactor)

        val energyFootprint = electricityUsage * electricityEmissionFactor

        val foodFootprint = (meatConsumption * meatEmissionFactor) +
                (dairyConsumption * dairyEmissionFactor)

        // Total de pegada de carbono
        return transportFootprint + energyFootprint + foodFootprint
    }

    // Função para resetar o feedback de sucesso
    fun resetSuccessMessage() {
        _showSuccessMessage.value = false
    }
}