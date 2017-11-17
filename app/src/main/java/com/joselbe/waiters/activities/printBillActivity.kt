package com.joselbe.waiters.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.joselbe.waiters.R
import com.joselbe.waiters.models.Dish
import com.joselbe.waiters.models.Tables
import kotlinx.android.synthetic.main.activity_print_bill.*

class printBillActivity : AppCompatActivity() {
    companion object {
        val ARG_TABLE = "ARG_TABLE"
        fun intent(context: Context, tablepos : Int): Intent {
            val intent = Intent(context, printBillActivity::class.java)
            intent.putExtra(ARG_TABLE, tablepos)

            return intent
        }
    }

    lateinit var list : ListView
    var tablepos = 0 // Mesa con la que trabajamos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_print_bill)

        //parametros
        tablepos = intent.getIntExtra(ARG_TABLE, 0)

        list = findViewById<ListView>(R.id.table_list_bill)
        list.adapter = ListaAdapter(this,tablepos) //asignamos el adapter a la lista

        //asignamos a items del layout que no son list
        billtextpricetotal.text = Tables[tablepos].getTotalPrice().toString()
        billtextmesa.text = "Mesa " + (tablepos+1).toString()


        //Pago de la factura button
        botonPagado.setOnClickListener {
            Tables[tablepos].clearAll() //vaciamos los platos
            finish()
        }

    }



    internal class ListaAdapter(context: Context, val posTable : Int) : BaseAdapter() {

        private val mInflator: LayoutInflater

        init {
            this.mInflator = LayoutInflater.from(context)
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            //componemos la vista de la celda personalizada

            val view: View?
            val vh: ListRowHolder
            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.celda_bill, parent, false)
                vh = ListRowHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }

            //asignamos a la vista
            vh.titulo.text = Tables[posTable].getDish(position)?.name
            vh.price.text = Tables[posTable].getDish(position)?.price.toString()
            vh.imagen.setImageResource(Tables[posTable].getDish(position)?.img!!)
            return view!!

        }

        override fun getItem(position: Int): Dish? {
            val mesa =  Tables[posTable]
            val platoMesa = mesa.getDish(position)
            return platoMesa
        }

        override fun getItemId(position: Int): Long = 0
        override fun getCount() = Tables[posTable].getDishesCount()


        //Clase Holder de Bill
        internal class ListRowHolder(row: View?) {
            public val titulo: TextView
            public val price: TextView
            public val imagen : ImageView

            init {

                price = row?.findViewById<TextView>(R.id.txtbillprice)!!
                titulo = row?.findViewById<TextView>(R.id.txtbilltitulo)!!
                imagen   = row?.findViewById<ImageView>(R.id.imageBill)!!

            }
        }

    }
}
