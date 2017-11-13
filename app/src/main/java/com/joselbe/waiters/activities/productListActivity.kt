package com.joselbe.waiters.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.joselbe.waiters.R
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.joselbe.waiters.models.CONSTANT_CURRENCY
import com.joselbe.waiters.models.menus
import org.jetbrains.anko.image

class productListActivity : FragmentActivity() {

    companion object {
        val MENU_SELECTED = "MENU_SELECTED"

        fun intent(context: Context): Intent {
            val intent = Intent(context, productListActivity::class.java)
            return intent
        }
    }

    lateinit var list : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_product_list)

        list = findViewById<ListView>(R.id.table_list_product)
        list.adapter = ListaAdapter(this) //asignamos el adapter a la lista

        // Nos enteramos de que se ha pulsado un elemento de la lista así:
        list.setOnItemClickListener { parent, view, position, id ->
            Snackbar.make(findViewById<View>(android.R.id.content), "Se ha hecho click ${position} ", Snackbar.LENGTH_LONG).show()
        }

    }



    // ..............................................................
    //  ... ADAPTADOR DE LA LISTA .......
    // ..............................................................

    internal class ListaAdapter(context: Context) : BaseAdapter() {

        private val mInflator: LayoutInflater

        init {
            this.mInflator = LayoutInflater.from(context)
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            //componemos la vista de la celda personalizada

            val view: View?
            val vh: ListRowHolder
            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.celda_menus, parent, false)
                vh = ListRowHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }

            //asignamos a la vista los valores
            val menuItem = menus.getMenuItem(position)
            vh.titulo.text = menuItem.name
            vh.precio.text = menuItem.price.toString() + " " + CONSTANT_CURRENCY
            vh.image.setImageResource(menuItem.image)


            //Faltan los alergenicos. Los asignamos de forma que solo se muestren los que tienen

            Resourceof(vh.alg_1,1,menuItem.alg_Leche)
            Resourceof(vh.alg_2,2,menuItem.alg_altramuces)
            Resourceof(vh.alg_3,3,menuItem.alg_apio)
            Resourceof(vh.alg_4,4,menuItem.alg_cacahuete)
            Resourceof(vh.alg_5,5,menuItem.alg_crustaceos)
            Resourceof(vh.alg_6,6,menuItem.alg_ftutsecos)
            Resourceof(vh.alg_7,7,menuItem.alg_gluten)
            Resourceof(vh.alg_8,8,menuItem.alg_huevo)
            Resourceof(vh.alg_9,9,menuItem.alg_moluscos)
            Resourceof(vh.alg_10,10,menuItem.alg_mostaza)
            Resourceof(vh.alg_11,11,menuItem.alg_pescado)
            Resourceof(vh.alg_12,12,menuItem.alg_sesamo)
            Resourceof(vh.alg_13,13,menuItem.alg_soja)
            Resourceof(vh.alg_14,14,menuItem.alg_sulfitos)

            return view!!

        }

        private fun Resourceof(image : ImageView, item : Int, value : Int) {

            //Control imagen activa / desactiva
            image.alpha = 1f
            if (value == 0){
                image.alpha = 0.2f //para que salga deshabilitado en la celda
            }
            when(item){
                1 -> image.setImageResource(R.mipmap.alergenolacteo)
                2 -> image.setImageResource(R.mipmap.alergenoaltramuces)
                3 -> image.setImageResource(R.mipmap.alergenoapio)
                4 -> image.setImageResource(R.mipmap.alergenocachaute)
                5 -> image.setImageResource(R.mipmap.alergenocrustaceo)
                6 -> image.setImageResource(R.mipmap.alergenofrutos)
                7 -> image.setImageResource(R.mipmap.alergenogluten)
                8 -> image.setImageResource(R.mipmap.alergenohuevos)
                9 -> image.setImageResource(R.mipmap.alergenomolusco)
                10 -> image.setImageResource(R.mipmap.alergenomostaza)
                11 -> image.setImageResource(R.mipmap.alergenopescado)
                12 -> image.setImageResource(R.mipmap.alergenosesamo)
                13 -> image.setImageResource(R.mipmap.alergenosoja)
                14 -> image.setImageResource(R.mipmap.alergenoazufre)
            }
        }


        override fun getItem(position: Int): Any {
            return menus.getMenuItem(position)
        }

        override fun getItemId(position: Int): Long = 0
        override fun getCount() = menus.count


        //Clase Holder
        internal class ListRowHolder(row: View?) {
            public val titulo: TextView
            public val precio: TextView
            public val image: ImageView

            //alergenicos
            public val alg_1: ImageView
            public val alg_2: ImageView
            public val alg_3: ImageView
            public val alg_4: ImageView
            public val alg_5: ImageView
            public val alg_6: ImageView
            public val alg_7: ImageView
            public val alg_8: ImageView
            public val alg_9: ImageView
            public val alg_10: ImageView
            public val alg_11: ImageView
            public val alg_12: ImageView
            public val alg_13: ImageView
            public val alg_14: ImageView


            init {

                titulo = row?.findViewById<TextView>(R.id.name)!!
                precio = row?.findViewById<TextView>(R.id.price)!!
                image = row?.findViewById<ImageView>(R.id.imageproduct)!!
                alg_1 = row?.findViewById<ImageView>(R.id.alg_1)!!
                alg_2 = row?.findViewById<ImageView>(R.id.alg_2)!!
                alg_3 = row?.findViewById<ImageView>(R.id.alg_3)!!
                alg_4 = row?.findViewById<ImageView>(R.id.alg_4)!!
                alg_5 = row?.findViewById<ImageView>(R.id.alg_5)!!
                alg_6 = row?.findViewById<ImageView>(R.id.alg_6)!!
                alg_7 = row?.findViewById<ImageView>(R.id.alg_7)!!
                alg_8 = row?.findViewById<ImageView>(R.id.alg_8)!!
                alg_9 = row?.findViewById<ImageView>(R.id.alg_9)!!
                alg_10 = row?.findViewById<ImageView>(R.id.alg_10)!!
                alg_11 = row?.findViewById<ImageView>(R.id.alg_11)!!
                alg_12 = row?.findViewById<ImageView>(R.id.alg_12)!!
                alg_13 = row?.findViewById<ImageView>(R.id.alg_13)!!
                alg_14 = row?.findViewById<ImageView>(R.id.alg_14)!!

                //TODO: Faltan los alergenicos
            }
        }

    }
}
