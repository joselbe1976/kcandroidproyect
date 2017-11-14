package com.joselbe.waiters.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.joselbe.waiters.R
import com.joselbe.waiters.fragments.TableDetailFragment
import com.joselbe.waiters.fragments.TableListFragment
import com.joselbe.waiters.models.Tables
import com.joselbe.waiters.models.menus
import com.joselbe.waiters.models.table


class TablesActivity : AppCompatActivity() , TableListFragment.OnTableSelectedListener , TableDetailFragment.OnTableDetailListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        if (findViewById<View>(R.id.table_list_fragments) != null) {

            // Comprobamos primero que no tenemos ya añadido el fragment a nuestra jerarquía
            if (fragmentManager.findFragmentById(R.id.table_list) == null) {
                val fragment = TableListFragment.newInstance()
                fragmentManager.beginTransaction().add(R.id.table_list_fragments, fragment).commit()
            }

            if (findViewById<View>(R.id.table_detail) != null) {
                // Comprobamos primero que no tenemos ya añadido el fragment a nuestra jerarquía
                if (fragmentManager.findFragmentById(R.id.table_detail) == null) {
                    val fragment = TableDetailFragment.newInstance(0)
                    fragmentManager.beginTransaction().add(R.id.table_detail, fragment).addToBackStack("2").commit()
                }
            }

        }

    }



    //Listener de Mesas
    override fun onTableSelected(table: table?, position: Int) {
         val fragment = TableDetailFragment.newInstance(position)
       //Si es Fragment = Tablet
        if (findViewById<View>(R.id.table_detail) != null) {
                fragmentManager.beginTransaction().replace(R.id.table_detail, fragment).commit()
        }
        else
        {
             fragmentManager.beginTransaction().replace(R.id.table_list_fragments, fragment).addToBackStack("3").commit()
        }
    }

    //listener de Detail Table.
    override fun onTableAddDish(tablePos: Int) {
        //lanzamos el Intent
        val intent = productListActivity.intent(this, tablePos)
        startActivity(intent)
    }


}
