package com.iglo.wigsapplicationn.activity.product

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.iglo.wigsapplicationn.databinding.ItemProductLayoutBinding
import com.iglo.common.entity.Product

class ProductAdapter(
    val context: Context,
    val startSupportActionModeClick: (Product) -> Unit,
    val getSelection: () -> List<Product>,
    val toggleClick:(Product)->Unit
): RecyclerView.Adapter<ProductAdapterViewHolder>() {
    val differ = AsyncListDiffer<Product>(this, itemCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapterViewHolder {
        return ProductAdapterViewHolder(
            ItemProductLayoutBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductAdapterViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.data = data
        holder.binding.cardViewItemProduct.setOnClickListener{
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("PRODUCT_CODE", data.productCode)
            context.startActivity(intent)
        }

        holder.binding.isSelected = getSelection().contains(data)
        holder.binding.buyButton.setOnLongClickListener {
            startSupportActionModeClick(data)
            true
        }
        holder.binding.buyButton.setOnClickListener {
            toggleClick(data)
        }



        if (data.discount != 0) {
            holder.binding.harga.paintFlags =  Paint.STRIKE_THRU_TEXT_FLAG
            holder.binding.discountVisible = true
            holder.binding.price = discount(data)
        } else {
            holder.binding.discountVisible = false
            holder.binding.price = data.price
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitData(data: List<Product>){
        differ.submitList(data)
    }

    fun discount(data: Product): Double {
        val discount = data.discount * data.price / 100
        return data.price - discount
    }

    fun clearSelection(changes: (() -> Unit)?){

        val toggleDiffUtil = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = differ.currentList.size

            override fun getNewListSize(): Int = differ.currentList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return !getSelection().contains(differ.currentList[oldItemPosition])
            }
        }
        val differ = DiffUtil.calculateDiff(toggleDiffUtil)
        changes?.invoke()
        differ.dispatchUpdatesTo(this)
    }


    fun toggleSelection(product: Product, changes: () -> Unit) {
        val toggleDiffUtil = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = differ.currentList.size

            override fun getNewListSize(): Int = differ.currentList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return differ.currentList[oldItemPosition] != product
            }
        }
        val differ = DiffUtil.calculateDiff(toggleDiffUtil)
        changes()
        differ.dispatchUpdatesTo(this)
    }

    companion object{
        val itemCallback = object: DiffUtil.ItemCallback<Product>(){
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.productCode == newItem.productCode
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return true
            }

        }
    }
}

class ProductAdapterViewHolder(
    val binding: ItemProductLayoutBinding ): RecyclerView.ViewHolder(binding.root)