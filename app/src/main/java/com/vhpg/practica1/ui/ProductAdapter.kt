package com.vhpg.practica1.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vhpg.practica1.R
import com.vhpg.practica1.data.db.model.ProductEntity
import com.vhpg.practica1.databinding.ProductElementBinding

class ProductAdapter(
    private var onProductClick: (ProductEntity) -> Unit
): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private var products: List<ProductEntity> = emptyList()

    class ViewHolder(private val binding: ProductElementBinding): RecyclerView.ViewHolder(binding.root){
        val ivIcon = binding.ivIcon
        fun bind(product: ProductEntity){




            val imageResource = when (product.category) {
                0-> R.drawable.cat0
                1 -> R.drawable.cat1
                2 -> R.drawable.cat2
                3 -> R.drawable.cat3
                4 -> R.drawable.cat4
                5 -> R.drawable.cat5
                6 -> R.drawable.cat6
                7 -> R.drawable.cat7
                else -> R.drawable.cat0
            }
            binding.apply {
                tvName.text = product.name
                ivIcon.setImageResource(imageResource)
                tvDesc.text = product.description
                tvStock.text = product.stock.toString()
                tvPrice.text = "$ ${product.price.toString()}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductElementBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])

        holder.itemView.setOnClickListener {
            onProductClick(products[position])
        }

        holder.ivIcon.setOnClickListener {

        }
    }
    fun updateList(list:List<ProductEntity>){
        products = list
        notifyDataSetChanged()
    }
}