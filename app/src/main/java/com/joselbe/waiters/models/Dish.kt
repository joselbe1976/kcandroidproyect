package com.joselbe.waiters.models

import java.io.Serializable

/**
 *  Plato . Serializable
 */
data class Dish(val idDish : Int, val name : String, val price : Float, val img : Int, val costumerVariants : String ) : Serializable {
}