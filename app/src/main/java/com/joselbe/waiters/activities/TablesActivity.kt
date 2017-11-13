package com.joselbe.waiters.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.joselbe.waiters.R
import com.joselbe.waiters.fragments.TableListFragment
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

        if (findViewById<View>(R.id.table_list_fragments) != null) {

            // Comprobamos primero que no tenemos ya añadido el fragment a nuestra jerarquía
            if (fragmentManager.findFragmentById(R.id.table_list_fragments) == null) {
                val fragment = TableListFragment.newInstance()
                fragmentManager.beginTransaction().add(R.id.table_list_fragments, fragment).commit()
            }
        }
        else
        {
          // lazar aactivity normal como siempre con Intent
        }
        Snackbar.make(findViewById<View>(android.R.id.content), "Se ha hecho click ${position}", Snackbar.LENGTH_LONG).show()
    }

}
