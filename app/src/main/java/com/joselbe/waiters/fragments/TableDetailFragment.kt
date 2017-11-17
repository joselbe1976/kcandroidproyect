package com.joselbe.waiters.fragments


import android.app.Activity
import android.content.Context
import android.media.Image
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

import com.joselbe.waiters.R
import com.joselbe.waiters.models.Dish
import com.joselbe.waiters.models.Tables
import com.joselbe.waiters.models.table


class TableDetailFragment : android.app.Fragment(){


    companion object {
        val ARG_TABLE = "ARG_TABLE"

        fun newInstance(tablepos : Int): TableDetailFragment {
            val fragment = TableDetailFragment()

            val arguments = Bundle()
            arguments.putInt(ARG_TABLE, tablepos)
            fragment.arguments = arguments

            return fragment
        }
    }

    //Delegado
    interface OnTableDetailListener {
        fun onTableAddDish(tablePos: Int)
        fun onTablePrintBill(tablePos: Int)
    }


    lateinit var root: View
    lateinit var list : ListView
    var tablepos = 0 //mesa com la que trabajamos

    //Para comunicar Fragment con Activity
    private var onTableDetailListener: OnTableDetailListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_table_detail, container, false)

            //Pillamos la posicion que nos viene como akrgument
            tablepos = arguments?.getInt(ARG_TABLE) ?: 0

            list = root.findViewById<ListView>(R.id.table_detail_list)
            list.adapter = ListaAdapter(activity, tablepos) //creo el adaptador

            //listerner Añadir PLatos
            root.findViewById<FloatingActionButton?>(R.id.add_dish)?.setOnClickListener { v: View ->
                //avisamos a la actividad que lance una actity para mostrar la lista de platos a seleciconae
                onTableDetailListener?.onTableAddDish(tablepos)
            }

            //Impresion factura de la mesa
            root.findViewById<FloatingActionButton?>(R.id.print_bill)?.setOnClickListener { v: View ->

                onTableDetailListener?.onTablePrintBill(tablepos)
            }

        }
        return root
    }

    override fun onResume() {
        super.onResume()
        list.adapter = ListaAdapter(activity, tablepos) //Reasigno para refrescar

    }



    //ciclo de vida del Fragment
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context)
    }

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        onTableDetailListener = null
    }

    fun commonAttach(listener: Any?) {
        if (listener is OnTableDetailListener) {
            onTableDetailListener = listener
        }
    }



// ..............................................................
    //  ... ADAPTADOR DE LA LISTA .......
    // ..............................................................

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
                view = this.mInflator.inflate(R.layout.celda_platos_mesa, parent, false)
                vh = ListRowHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }

            //asignamos a la vista
            vh.titulo.text = Tables[posTable].getDish(position)?.name
            vh.textoCliente.text = Tables[posTable].getDish(position)?.costumerVariants
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


        //Clase Holder
        internal class ListRowHolder(row: View?) {
            public val titulo: TextView
            public val textoCliente: TextView
            public val imagen : ImageView

            init {

                titulo = row?.findViewById<TextView>(R.id.platotitulo)!!
                textoCliente = row?.findViewById<TextView>(R.id.textPlatoCliente)!!
                imagen   = row?.findViewById<ImageView>(R.id.platoFoto)!!

            }
        }

    }





}
