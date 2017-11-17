package com.joselbe.waiters.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.View
import com.joselbe.waiters.R
import com.joselbe.waiters.models.Tables
import com.joselbe.waiters.models.menus
import kotlinx.android.synthetic.main.activity_product_detail.*

class productDetailActivity : AppCompatActivity() {

    companion object {
        val ARG_TABLE = "ARG_TABLE"
        val ARG_PRODUCT = "ARG_PRODUCT"

        fun intent(context: Context, tablepos : Int, productpos : Int): Intent {
            val intent = Intent(context, productDetailActivity::class.java)
            intent.putExtra(ARG_TABLE, tablepos)
            intent.putExtra(ARG_PRODUCT, productpos)
            return intent
        }
    }

    var tablepos = 0 // Mesa con la que trabajamos
    var productpos = 0 // Producto seleciconado en la lista


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        //parametros
        tablepos = intent.getIntExtra(ARG_TABLE, 0)
        productpos = intent.getIntExtra(ARG_PRODUCT, 0)


        //pintamos los datos. Usamos en este caso EXTENSION de Kotlin
        proddetailtitulo.text = menus.getMenuItem(productpos).name
        productdetailprice.text = menus.getMenuItem(productpos).price.toString()
        prodctdetailimage.setImageResource(menus.getMenuItem(productpos).image)



        //listerner Añadir PLatos

        productdetailbutton.setOnClickListener {
            //Tañadimos a la mesa este producto.
            val menu = menus.getMenuItem(productpos)
            val mesa = Tables[tablepos]
            val costumerText = productdetailcostumer.text.toString() //lo que haya puesto el cliente

            mesa.addDish(menu.idMenu, menu.name, menu.price, menu.image,costumerText )

            // finalizamos actividad
             finish()
        }

        //boton de volver
        gobackDetaiLproduct.setOnClickListener {
            finish()
        }


    }
}
