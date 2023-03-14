package com.example.calculadorastates.model

import java.math.BigDecimal

class CalculadoraDominioIE {

    private var visualizador = "0"
    private var operando1 = ""
    private var operador = ""
    private var operando2 = ""

    val DIGITOS = "0123456789"
    val DOT = "."
    val EQUALS = "="
    val SIGN = "+/-"

    private var estadoCalculdora = EstadosCalculdora.INICIAL

    enum class EstadosCalculdora {
        INICIAL, DEFINIENDO_OPERANDO1, DEFINIENDO_OPERADOR, DEFININDO_OPERANDO2
    }

    fun ingresarElementoOperando(elementoOperando: String) {
        when (estadoCalculdora) {
            EstadosCalculdora.INICIAL -> if (DIGITOS.contains(elementoOperando)) {
                visualizador = elementoOperando
                estadoCalculdora = EstadosCalculdora.DEFINIENDO_OPERANDO1
            } else if (DOT.contains(elementoOperando)) {
                visualizador = "0$elementoOperando"
                estadoCalculdora = EstadosCalculdora.DEFINIENDO_OPERANDO1
            } else if (SIGN.contains(elementoOperando) && visualizador.equals(
                    "0",
                    ignoreCase = true
                )
            ) {
            } else if (SIGN.contains(elementoOperando) && !visualizador.equals(
                    "0",
                    ignoreCase = true
                )
            ) {
                val bigDecimal1 = BigDecimal(visualizador)
                val bigDecimal2 = BigDecimal("-1")
                visualizador = bigDecimal1.multiply(bigDecimal2).toString()
            }
            EstadosCalculdora.DEFINIENDO_OPERANDO1 -> if (DIGITOS.contains(elementoOperando)) {
                visualizador += elementoOperando
            } else if (DOT.contains(elementoOperando) && !visualizador.contains(DOT)) {
                visualizador += elementoOperando
            } else if (DOT.contains(elementoOperando) && visualizador.contains(DOT)) {
            } else if (SIGN.contains(elementoOperando)) {
                val bigDecimal1 = BigDecimal(visualizador)
                val bigDecimal2 = BigDecimal("-1")
                visualizador = bigDecimal1.multiply(bigDecimal2).toString()
            }
            EstadosCalculdora.DEFINIENDO_OPERADOR -> if (DIGITOS.contains(elementoOperando)) {
                visualizador = elementoOperando
                estadoCalculdora = EstadosCalculdora.DEFININDO_OPERANDO2
            } else if (DOT.contains(elementoOperando)) {
                visualizador = "0$elementoOperando"
                estadoCalculdora = EstadosCalculdora.DEFININDO_OPERANDO2
            } else if (SIGN.contains(elementoOperando)) {
            }
            EstadosCalculdora.DEFININDO_OPERANDO2 -> if (DIGITOS.contains(elementoOperando)) {
                visualizador += elementoOperando
            } else if (DOT.contains(elementoOperando) && !visualizador.contains(DOT)) {
                visualizador += elementoOperando
            } else if (DOT.contains(elementoOperando) && visualizador.contains(DOT)) {
            } else if (SIGN.contains(elementoOperando)) {
                val bigDecimal1 = BigDecimal(visualizador)
                val bigDecimal2 = BigDecimal("-1")
                visualizador = bigDecimal1.multiply(bigDecimal2).toString()
            }
        }
    }

    @Throws(Exception::class)
    fun ingresarOperador(operador: String) {
        when (estadoCalculdora) {
            EstadosCalculdora.INICIAL -> if (operador.equals(EQUALS, ignoreCase = true)) {
            } else if (!operador.equals(
                    EQUALS, ignoreCase = true
                )
            ) {
                operando1 = visualizador
                this.operador = operador
                estadoCalculdora = EstadosCalculdora.DEFINIENDO_OPERADOR
            }
            EstadosCalculdora.DEFINIENDO_OPERANDO1 -> if (operador.equals(
                    EQUALS,
                    ignoreCase = true
                )
            ) {
            } else if (!operador.equals(
                    EQUALS, ignoreCase = true
                )
            ) {
                operando1 = visualizador
                this.operador = operador
                estadoCalculdora = EstadosCalculdora.DEFINIENDO_OPERADOR
            }
            EstadosCalculdora.DEFINIENDO_OPERADOR -> if (operador.equals(
                    EQUALS,
                    ignoreCase = true
                )
            ) {
            } else if (!operador.equals(
                    EQUALS, ignoreCase = true
                )
            ) {
                this.operador = operador
            }
            EstadosCalculdora.DEFININDO_OPERANDO2 ->            // esta lo interesante
                if (operador.equals(EQUALS, ignoreCase = true)) {
                    operando2 = visualizador
                    visualizador = realizarOperacion()
                    estadoCalculdora = EstadosCalculdora.INICIAL
                } else if (!operador.equals(EQUALS, ignoreCase = true)) {
                    operando2 = visualizador
                    operando1 = realizarOperacion()
                    visualizador = operando1
                    this.operador = operador
                    estadoCalculdora = EstadosCalculdora.DEFINIENDO_OPERADOR
                }
        }
    }

    fun borrarCalculadora() {
        visualizador = "0"
        operando1 = ""
        operador = ""
        operando2 = ""
        estadoCalculdora = EstadosCalculdora.INICIAL
    }


    //fun getVisualizador(): String? {
    fun getVisualizador(): String {
        return visualizador
    }


    /**
     * Se realiza la operacion indicada y se entrega el resultado.
     * Si la operacion es realizada y regresada a Double, se pierde precision en la notacion cientifica,
     * por lo que es mejor manejar String con DecimalFormat.
     * @return El resultado de la operacion (sin notacion cientifica y con representacion en String)
     * @throws Exception
     */
    @Throws(Exception::class)
    private fun realizarOperacion(): String {
        val resultado: String
        resultado = if (operador.equals("+", ignoreCase = true)) {
            val bigDecimal1 = BigDecimal(operando1)
            val bigDecimal2 = BigDecimal(operando2)
            bigDecimal1.add(bigDecimal2).toString()
        } else if (operador.equals("-", ignoreCase = true)) {
            val bigDecimal1 = BigDecimal(operando1)
            val bigDecimal2 = BigDecimal(operando2)
            bigDecimal1.subtract(bigDecimal2).toString()
        } else if (operador.equals("*", ignoreCase = true)) {
            val bigDecimal1 = BigDecimal(operando1)
            val bigDecimal2 = BigDecimal(operando2)
            bigDecimal1.multiply(bigDecimal2).toString()
        } else if (operador.equals("/", ignoreCase = true)) {
            val bigDecimal1 = BigDecimal(operando1)
            val bigDecimal2 = BigDecimal(operando2)
            bigDecimal1.divide(bigDecimal2).toString()
        } else {
            throw Exception("operacion invalida")
        }
        return resultado + ""
    }


}