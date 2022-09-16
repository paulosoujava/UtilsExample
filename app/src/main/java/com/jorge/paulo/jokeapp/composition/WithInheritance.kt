package com.jorge.paulo.jokeapp.composition

// 1 Você tem uma classe abstrata, Pizza, com um prepare().
abstract class Pizza() {
    abstract fun prepare()
}

// 2 CheesePizzae VeggiePizzasão classes filhas de Pizza.
class CheesePizza() : Pizza() {
    override fun prepare() {
        println("Prepared a Cheese Pizza")
    }
}

class VeggiePizza() : Pizza() {
    override fun prepare() {
        println("Prepared a Veggie Pizza")
    }
}

fun main() {
    // 3
/*
Como a classe filha é uma classe pai, você pode usar
 a CheesePizzaou a VeggiePizzaem qualquer
 lugar onde precisar de um arquivo Pizza.

 */
    val cheesePizza: Pizza = CheesePizza()
    val veggiePizza: Pizza = VeggiePizza()
    val menu = listOf(cheesePizza, veggiePizza)
    for (pizza in menu) {
        // 4
        pizza.prepare()
        /*
        Mesmo quando cheesePizzae veggiePizzatipos
        estão sendo convertidos para Pizza,
         o prepare( )invoca a implementação fornecida
         pela respectiva classe filha, mostrando
         um comportamento polimórfico. Isso ocorre
         porque Pizzadefine a operação que você pode
          invocar, enquanto o objeto referenciado
          define a implementação real.

         */
    }
}
