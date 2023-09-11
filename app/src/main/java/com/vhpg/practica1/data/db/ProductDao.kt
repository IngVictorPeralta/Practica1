package com.vhpg.practica1.data.db

import androidx.room.*
import com.vhpg.practica1.data.db.model.ProductEntity
import com.vhpg.practica1.util.Constants.DATABASE_PRODUCT_TABLE

@Dao
interface ProductDao {

    //Create
    @Insert
    suspend fun insertProduct(product: ProductEntity)

    //Read
    @Query("SELECT * FROM ${DATABASE_PRODUCT_TABLE}")
    suspend fun getAllProducts(): List<ProductEntity>

    //Update
    @Update
    suspend fun updateProduct(product: ProductEntity)

    //Delete
    @Delete
    suspend fun deleteProduct(product: ProductEntity)
}