package com.iglo.wigsapplicationn.activity.checkout


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.iglo.common.entity.Product
import com.iglo.wigsapplicationn.databinding.ItemCheckoutLayoutBinding

class CheckoutActivityAdapter(
    private val getTotalPrice: (Product, Double) -> Unit
): RecyclerView.Adapter<CheckoutAdapterViewHolder>() {
    private val differ = AsyncListDiffer<Product>(this, itemCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutAdapterViewHolder {
        return CheckoutAdapterViewHolder(
            ItemCheckoutLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CheckoutAdapterViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.data = data
        holder.binding.totalPrice = totalPrice(data, 1)
        holder.binding.pcs.addTextChangedListener {
            holder.binding.totalPrice = totalPrice(data, it?.ifBlank { 0 }.toString().toInt())
            getTotalPrice(data, totalPrice(data, it?.ifBlank { 0 }.toString().toInt()))
        }

    }

    override fun getItemCount(): Int = differ.currentList.size



    fun submitData(data: List<Product>){
        differ.submitList(data)
    }


    fun totalPrice(data: Product, pcs: Int): Double{
        val discount = data.discount * data.price / 100
        val hargaDiskon = data.price - discount
        val subtotal = hargaDiskon * pcs
        return subtotal
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

class CheckoutAdapterViewHolder(
    val binding: ItemCheckoutLayoutBinding
) : RecyclerView.ViewHolder(binding.root)