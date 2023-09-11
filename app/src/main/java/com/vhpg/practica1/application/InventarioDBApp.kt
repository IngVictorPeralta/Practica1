package com.vhpg.practica1.application

import android.app.Application
import com.vhpg.practica1.data.ProductRepository
import com.vhpg.practica1.data.db.InventaryDatabase

class InventarioDBApp(): Application() {
    private val database by lazy {
        InventaryDatabase.getDatabase(this@InventarioDBApp)
    }

    val repository by lazy{
        ProductRepository(database.productDao())
    }
}