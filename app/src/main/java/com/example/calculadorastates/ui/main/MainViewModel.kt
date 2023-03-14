package com.example.calculadorastates.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculadorastates.model.CalculadoraDominioIE
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val calculadoraDominioIE : CalculadoraDominioIE = CalculadoraDominioIE()

    private val calculatorEventChannel = Channel<CalculatorEvent>()
    val calculatorEvent = calculatorEventChannel.receiveAsFlow()

    fun onClickOperando(value:String) = viewModelScope.launch{
        calculadoraDominioIE.ingresarElementoOperando(value)
        calculatorEventChannel.send(CalculatorEvent.ShowValue(calculadoraDominioIE.getVisualizador()))
    }

    fun onClickOperador(value:String) = viewModelScope.launch{
        calculadoraDominioIE.ingresarOperador(value)
        calculatorEventChannel.send(CalculatorEvent.ShowValue(calculadoraDominioIE.getVisualizador()))
    }

    fun onClickBorrar() = viewModelScope.launch{
        calculadoraDominioIE.borrarCalculadora()
        calculatorEventChannel.send(CalculatorEvent.ShowValue(calculadoraDominioIE.getVisualizador()))
    }

    sealed class CalculatorEvent {
        data class ShowValue(val value: String) : CalculatorEvent()
    }

}