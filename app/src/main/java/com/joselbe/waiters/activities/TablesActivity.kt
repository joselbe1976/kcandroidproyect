package com.joselbe.waiters.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.joselbe.waiters.R
import com.joselbe.waiters.fragments.TableListFragment
import com.joselbe.waiters.models.menus
import com.joselbe.waiters.models.table


class TablesActivity : AppCompatActivity() , TableListFragment.OnTableSelectedListener{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        if (findViewById<View>(R.id.table_list_fragments) != null) {

            // Comprobamos primero que no tenemos ya añadido el fragment a nuestra jerarquía
            if (fragmentManager.findFragmentById(R.id.table_list_fragments) == null) {
                val fragment = TableListFragment.newInstance()
                fragmentManager.beginTransaction().add(R.id.table_list_fragments, fragment).commit()
            }
        }

        // mesa 1 por defecto al iniciar la aplicacion

    }


    override fun onTableSelected(table: table?, position: Int) {
/*

        if (findViewById<View>(R.id.table_list_fragments) != null) {

            // Comprobamos primero que no tenemos ya añadido el fragment a nuestra jerarquía
            if (fragmentManager.findFragmentById(R.id.table_list_fragments) == null) {
                   val fragment = productListFragment.newInstance()
                fragmentManager.beginTransaction().add(R.id.table_list_fragments, fragment).commit()
            }
            else{
                val fragment = productListFragment.newInstance()
                val fragment2 = TableListFragment.newInstance()
              // fragmentManager.beginTransaction().remove(fragment2).commit()

                fragmentManager.beginTransaction().replace(R.id.table_list_fragments, fragment).addToBackStack("fragmentFicha").commit()

                       // .add(R.id.table_list_fragments, fragment).commit()

            }
        }

*/
        val intent = productListActivity.intent(this)
        startActivity(intent)


        //Snackbar.make(findViewById<View>(android.R.id.content), "Se ha hecho click ${position} . Numero menus en memoria: ${menus.count}", Snackbar.LENGTH_LONG).show()
    }

}
