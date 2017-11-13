package com.joselbe.waiters.fragments

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

import com.joselbe.waiters.R
import com.joselbe.waiters.models.Tables
import com.joselbe.waiters.models.table

/*
    Fragment de Lista de Mesas
 */
class TableListFragment : android.app.Fragment(){

    companion object {

        fun newInstance(): TableListFragment {
            val fragment = TableListFragment()
            return fragment
        }
    }

    //Delegado
    interface OnTableSelectedListener {
        fun onTableSelected(table: table?, position: Int)
    }


    lateinit var root: View

    //Para comunicar Fragment con Activity
    private var onTableSelectedListener: OnTableSelectedListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_table_list, container, false)
            val list = root.findViewById<ListView>(R.id.table_list)
            //val adapter = ArrayAdapter<table>(activity, android.R.layout.simple_list_item_1, Tables.toArray())
            list.adapter = TablesListaAdapter(activity)

            // Nos enteramos de que se ha pulsado un elemento de la lista así:
            list.setOnItemClickListener { parent, view, position, id ->
                // Aviso al listener
                onTableSelectedListener?.onTableSelected(Tables.get(position), position)
            }
        }

        return root
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
        onTableSelectedListener = null
    }

    fun commonAttach(listener: Any?) {
        if (listener is OnTableSelectedListener) {
            onTableSelectedListener = listener
        }
    }




    // ..............................................................
    //  ... ADAPTADOR DE LA LISTA .......
    // ..............................................................

    internal class TablesListaAdapter(context: Context) : BaseAdapter() {

        private val mInflator: LayoutInflater

        init {
            this.mInflator = LayoutInflater.from(context)
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            //componemos la vista de la celda personalizada

            val view: View?
            val vh: ListRowHolder
            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.celda_tables, parent, false)
                vh = ListRowHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }

            //asignamos a la vista
            vh.tituloMenu.text = Tables[position].name
            return view!!

        }

        override fun getItem(position: Int): Any {
            return Tables[position]
        }

        override fun getItemId(position: Int): Long = 0
        override fun getCount() = Tables.count


        //Clase Holder
        internal class ListRowHolder(row: View?) {
            public val tituloMenu: TextView

            init {

                tituloMenu = row?.findViewById<TextView>(R.id.tituloMenu)!!
            }
        }

    }




}
