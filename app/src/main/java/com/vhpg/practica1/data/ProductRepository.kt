package com.vhpg.practica1.data

import com.vhpg.practica1.data.db.ProductDao
import com.vhpg.practica1.data.db.model.ProductEntity

class ProductRepository(private val productDao:ProductDao){

    suspend fun insertProduct(product: ProductEntity){
        productDao.insertProduct(product)
    }

    suspend fun getAllProducts(): List<ProductEntity> = productDao.getAllProducts()

    suspend fun updateProduct(product: ProductEntity){
        productDao.updateProduct(product)
    }

    suspend fun deleteProduct(product: ProductEntity){
        productDao.deleteProduct(product)
    }
}