package com.joselbe.waiters.models

import java.io.Serializable

/**
 * Definicion del menu, o plato.
 */
class menuItem(val idMenu : Int, val name : String, val price : Float, val image : Int , val alg_gluten : Int = 0, val alg_crustaceos : Int = 0, val alg_huevo : Int = 0, val alg_pescado : Int = 0, val alg_cacahuete : Int = 0, val alg_soja : Int = 0, val alg_Leche : Int = 0, val alg_ftutsecos : Int = 0, val alg_apio : Int = 0, val alg_mostaza : Int = 0, val alg_sesamo : Int = 0, val alg_sulfitos : Int = 0, val alg_altramuces : Int = 0, val alg_moluscos : Int = 0) : Serializable {


}