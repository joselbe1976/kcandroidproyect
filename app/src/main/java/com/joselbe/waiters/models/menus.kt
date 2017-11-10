package com.joselbe.waiters.models

/**
 * Son los diferentes menus que puede elegir el cliente. Es un singleton
 */
object  menus {
    private var menus : MutableList<menuItem>? = null

    val count
        get() = menus?.size

    fun delMenu( position : Int){
        menus?.removeAt(position)
    }
    fun addMenu (idMenu : Int, name : String, price : Float, image : Int , alg_gluten : Int = 0, alg_crustaceos : Int = 0, alg_huevo : Int = 0, alg_pescado : Int = 0, alg_cacahuete : Int = 0, alg_soja : Int = 0, alg_Leche : Int = 0, alg_ftutsecos : Int = 0, alg_apio : Int = 0, alg_mostaza : Int = 0, alg_sesamo : Int = 0, alg_sulfitos : Int = 0, alg_altramuces : Int = 0, alg_moluscos : Int = 0){
        menus?.add(menuItem(idMenu , name , price , image  , alg_gluten , alg_crustaceos , alg_huevo , alg_pescado , alg_cacahuete , alg_soja , alg_Leche , alg_ftutsecos , alg_apio , alg_mostaza, alg_sesamo , alg_sulfitos , alg_altramuces , alg_moluscos))
    }

    fun getMenuItem(position : Int) = menus?.get(position)
    fun toArray() = menus?.toTypedArray()
}