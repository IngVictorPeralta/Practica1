package com.vhpg.practica1.ui

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.vhpg.practica1.R
import com.vhpg.practica1.application.InventarioDBApp
import com.vhpg.practica1.data.ProductRepository
import com.vhpg.practica1.data.db.model.ProductEntity
import com.vhpg.practica1.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var products: List<ProductEntity> = emptyList()
    private lateinit var repository: ProductRepository

    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = (application as InventarioDBApp).repository

        productAdapter = ProductAdapter(){product ->
            productClicked(product)
        }

        ///binding.rvProducts.layoutManager = LinearLayoutManager(this@MainActivity)
        ///binding.rvProducts.adapter = productAdapter

        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = productAdapter
        }
        //Toast.makeText(this, getString(R.string.h), Toast.LENGTH_SHORT).show()
        updateUI()
    }

    private fun updateUI(){
        lifecycleScope.launch{
            products = repository.getAllProducts()
            if(products.isNotEmpty()){
                binding.tvSinRegistros.visibility = View.INVISIBLE


            }else{
                binding.tvSinRegistros.visibility = View.VISIBLE
            }
            productAdapter.updateList(products)
        }
    }
    fun click(view: View){
        val dialog = ProductDialog(updateUI = {
            updateUI()
        },message = {text->
            message(text)

        })
        dialog.show(supportFragmentManager,"dialog")
        //Toast.makeText(this@MainActivity, "Nuevo", Toast.LENGTH_SHORT).show()
    }
    private fun productClicked(product: ProductEntity){
        //message()
        //Toast.makeText(this, "Click en el producto: ${product.name}", Toast.LENGTH_SHORT).show()
        val dialog = ProductDialog(newProduct = false, product = product, updateUI = {
            updateUI()
        },message = {id->
            message(id)
        })
        dialog.show(supportFragmentManager, "dialog")

    }

    private fun message(id:Int){
        Snackbar.make(binding.cl, getString(id),Snackbar.LENGTH_SHORT)
            .setTextColor(Color.parseColor("#FFFFFF"))
            .setBackgroundTint(Color.parseColor("#9E1734"))
            .show()
    }
}