package com.jorge.paulo.jokeapp.composition


import java.math.RoundingMode

// 1 Extraia as dimensões em uma classe separada — PizzaType— e Size.
sealed class PizzaType {
    data class Cheese(val cheeseName: String) : PizzaType()
    data class Veggie(val vegetables: List<String>) : PizzaType()
}

enum class Size(val value: Int) {
    LARGE(12), MED(8), SMALL(6);

    fun calculateArea(): Double {
        // Area of circle given diameter
        return (Math.PI / 4).toBigDecimal().setScale(2, RoundingMode.UP).toDouble() * value * value
    }
}

// 2 Faça com que a classe original se refira a uma instância da classe extraída.
// Aqui, a classe Pizza consiste em suas classes de duas dimensões.
class Pizza2(private val type: PizzaType, val size: Size) {
    fun prepare() {
        // 3
        println("Prepared ${size.name} sized $type pizza of area ${size.calculateArea()}")
        /*
        Faça com que a classe composta delegue qualquer cálculo relacionado ao tamanho
        para a Sizeclasse ou qualquer cálculo relacionado ao tipo para a PizzaTypeclasse.
         É assim que a classe composta cumpre sua responsabilidade: interagindo com os
         campos de instância.
         */
    }
}

fun main() {
    val largeCheesePizza = Pizza2(PizzaType.Cheese("Mozzarella"), Size.LARGE)
    val smallVeggiePizza = Pizza2(PizzaType.Veggie(listOf("Spinach", "Onion")), Size.SMALL)
    val orders = listOf(largeCheesePizza, smallVeggiePizza)

    orders.forEach {
        it.prepare()
    }

}