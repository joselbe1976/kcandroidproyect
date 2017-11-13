package com.joselbe.waiters.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.joselbe.waiters.R
import com.joselbe.waiters.models.*

import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.json.JSONObject
import java.net.URL
import java.util.*


/*
    Fragment de Lista de Mesas
 */
class TableListFragment : android.app.Fragment(){

    //creamos enumerado para las vistas del fragment (loaden o lista de mesas)
    enum class VIEW_INDEX (val index : Int){
        LOADING(0),
        TABLES(1)
    }

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
    lateinit var viewSwitcher : ViewSwitcher
    lateinit var list : ListView


    //Para comunicar Fragment con Activity
    private var onTableSelectedListener: OnTableSelectedListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_table_list, container, false)

            //Switch View del fragment
            viewSwitcher = root.findViewById(R.id.viewSwitcher) //Capturo el View Switcher
            viewSwitcher.setInAnimation(activity, android.R.anim.fade_in )  //indicamos la animacion de las vistas

            list = root.findViewById<ListView>(R.id.table_list)

            // Nos enteramos de que se ha pulsado un elemento de la lista así:
            list.setOnItemClickListener { parent, view, position, id ->
                // Aviso al listener
                onTableSelectedListener?.onTableSelected(Tables.get(position), position)
            }

            //descarga de los menus
            downloadTables()


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
    //  ... DESCARGA DE LAS MESAS
    // ..............................................................

    private fun downloadTables(){
            viewSwitcher.displayedChild = VIEW_INDEX.LOADING.index //primera view (barra descarga)

            if (menus.count != 0)
            {
                viewSwitcher.displayedChild = VIEW_INDEX.TABLES.index //Se cambia vista al principal que es la lista de mesas
                list.adapter = TablesListaAdapter(activity) //asignamos el adapter a la lista
                return
            }

            async(UI) {
                    val data: Deferred<Unit?> = bg {
                        dowloadData()
                    }
                    data.await() //hilo secundario
                    viewSwitcher.displayedChild = VIEW_INDEX.TABLES.index //Se cambia vista al principal que es la lista de mesas
                    list.adapter = TablesListaAdapter(activity) //asignamos el adapter a la lista
            }
    }

    //Descarga real de los datos de las mesas y deja actualizado el modelo que es Singleton
    private fun dowloadData() : Unit{
        try {
            // Nos descargamos la información de la carta
            val url = URL(CONSTANT_URL_JSON)
            val jsonString = Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next()

            // Analizamos los datos que nos acabamos de descargar
            val jsonRoot = JSONObject(jsonString)
            val data = jsonRoot.getJSONArray("plates") //cargamos array de Platos (plates)


            //recorrmeos cada plato.
            for (plate in 0..data.length()-1) {

                val dataMenu = data.getJSONObject(plate)

                val idMenu = dataMenu.getInt("idmenu")
                val image = dataMenu.getInt("image")
                val name = dataMenu.getString("name")
                val precio = dataMenu.getDouble ("price").toFloat()
                val alg_gluten = dataMenu.getInt("alg_gluten")
                val alg_crustaceos = dataMenu.getInt("alg_crustaceos")
                val alg_huevo = dataMenu.getInt("alg_huevo")
                val alg_pescado = dataMenu.getInt("alg_pescado")
                val alg_cacahuete = dataMenu.getInt("alg_cacahuete")
                val alg_soja = dataMenu.getInt("alg_soja")
                val alg_leche = dataMenu.getInt("alg_leche")
                val alg_frutsecos = dataMenu.getInt("alg_frutsecos")
                val alg_apio = dataMenu.getInt("alg_apio")
                val alg_mostaza = dataMenu.getInt("alg_mostaza")
                val alg_sesamo = dataMenu.getInt("alg_sesamo")
                val alg_sulfitos = dataMenu.getInt("alg_sulfitos")
                val alg_altramuces = dataMenu.getInt("alg_altramuces")
                val alg_moluscos = dataMenu.getInt("alg_moluscos")

                val iconResource = when (image) {
                    1 -> R.mipmap.icono_1
                    2 -> R.mipmap.icono_2
                    3 -> R.mipmap.icono_3
                    4 -> R.mipmap.icono_4
                    5 -> R.mipmap.icono_5
                    6 -> R.mipmap.icono_6
                    else ->  R.mipmap.icono_0
                }

                //añado al singleton
                menus.addMenu(idMenu, name, precio, iconResource,alg_gluten,alg_crustaceos,alg_huevo,alg_pescado,alg_cacahuete,alg_soja,alg_leche,alg_frutsecos,alg_apio,alg_mostaza,alg_sesamo,alg_sulfitos,alg_altramuces,alg_moluscos)

            }
        } catch (ex: Exception) {
            ex.printStackTrace()
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

            if (Tables[position].getDishesCount() == 0){
                vh.subtituloMenu.text = "Sin platos"
            }
            else{
                vh.subtituloMenu.text = "${Tables[position].getDishesCount() } platos"
            }


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
            public val subtituloMenu: TextView

            init {

                tituloMenu = row?.findViewById<TextView>(R.id.tituloMenu)!!
                subtituloMenu = row?.findViewById<TextView>(R.id.subtituloMenu)!!
            }
        }

    }




}
