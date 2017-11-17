package com.joselbe.waiters.models

import java.io.Serializable

/**
 * CLista de Mesas como singleton
 */
object Tables : Serializable {

    private var tables : List<table> = listOf(
            table(1,"Mesa 1"),
            table(2,"Mesa 2"),
            table(3,"Mesa 3"),
            table(4,"Mesa 4"),
            table(5,"Mesa 5")
    )

    val count
        get() = tables.size

    operator fun get(i: Int) = tables[i]

    fun toArray() = tables.toTypedArray()

    fun getTotalBillPrice(position : Int) : Float {
        return tables[position].getTotalPrice()
    }
}