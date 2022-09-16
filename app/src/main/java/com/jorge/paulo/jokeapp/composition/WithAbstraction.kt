package com.jorge.paulo.jokeapp.composition

abstract class BaconPizza : Pizza()

abstract class BananaPizza : Pizza()

class SmallBaconPizza : BaconPizza() {
    override fun prepare() {
        println("Preparou uma pequena pizza de bacon")
    }
}

class MediumBaconPizza : BaconPizza() {
    override fun prepare() {
        println("Preparou uma pizza de bacon média")
    }
}

class LargeBaconPizza : BaconPizza() {
    override fun prepare() {
        println("Preparou uma pizza de bacon grande")
    }
}

class SmallBananaPizza : BananaPizza() {
    override fun prepare() {
        println("Preparou uma pizza banana pequena")
    }
}

class MediumBananaPizza : BananaPizza() {
    override fun prepare() {
        println("Preparou uma pizza banana média")
    }
}

class LargeBananaPizza : BananaPizza() {
    override fun prepare() {
        println("Preparou uma pizza banana grande")
    }
}


