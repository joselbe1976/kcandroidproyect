package com.joselbe.waiters.models

import java.io.Serializable

/**
 * Table definition
 */
data class table(val idtable : Int, val name : String) : Serializable {

    val dishes : MutableList<Dish> = arrayListOf()

    // Lista de platos, fujcnones

    fun getDishesCount() : Int {
        if (dishes != null)
        {
            return dishes.size
        }
        else
        {
            return 0
        }
    }

    fun delDish( position : Int){
        dishes.removeAt(position)
    }
    fun addDish (idDish : Int, name : String, price : Float, img : Int,  costumerVariants : String ){
        dishes.add(Dish(idDish, name, price, img, costumerVariants))
    }
    fun getDish(position : Int) = dishes.get(position)

    override fun toString() : String {
        var text  = name
        return text
    }
}