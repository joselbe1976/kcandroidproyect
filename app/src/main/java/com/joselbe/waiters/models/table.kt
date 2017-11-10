package com.joselbe.waiters.models

import java.io.Serializable

/**
 * Table definition
 */
data class table(val idtable : Int, val name : String, val dishes : MutableList<Dish>? = null) : Serializable {


    constructor(idtable : Int, name : String) : this(idtable, name, null){}

    // Lista de platos, fujcnones

    fun getDishesCount() = dishes?.size

    fun delDish( position : Int){
        dishes?.removeAt(position)
    }
    fun addDish (idDish : Int, name : String, price : Float, img : Int,  costumerVariants : String ){
        dishes?.add(Dish(idDish, name, price, img, costumerVariants))
    }

}