package com.vhpg.practica1.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vhpg.practica1.data.db.model.ProductEntity
import com.vhpg.practica1.util.Constants

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class InventaryDatabase: RoomDatabase(){

    abstract fun productDao(): ProductDao

    companion object{
        @Volatile
         private var INSTANCE: InventaryDatabase? = null

        fun getDatabase(context: Context): InventaryDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InventaryDatabase::class.java,
                    Constants.DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}