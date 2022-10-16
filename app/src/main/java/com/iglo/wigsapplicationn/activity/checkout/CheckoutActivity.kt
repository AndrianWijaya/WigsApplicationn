package com.iglo.wigsapplicationn.activity.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.iglo.common.entity.Product
import com.iglo.wigsapplicationn.BR
import com.iglo.wigsapplicationn.R
import com.iglo.wigsapplicationn.databinding.ListCheckoutLayoutBinding
import com.iglo.wigsapplicationn.view_model.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutActivity : AppCompatActivity() {
    private val layoutResourceId = R.layout.list_checkout_layout
    lateinit var binding: ListCheckoutLayoutBinding

    val vm: ProductViewModel by viewModels()

    private val adapter = CheckoutActivityAdapter { product: Product, d: Double ->
        vm.subtotalMap[product] = d
        getTotalPrice()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(this), layoutResourceId, null, false
        )
        binding.lifecycleOwner = this
        setContentView(binding.root)
        binding.setVariable(BR.vm, vm)

        binding.recycler.adapter = adapter

        val productCodeSelected = intent.getStringArrayListExtra("EXTRA_DATA")


        vm.dataProduct.observe(this@CheckoutActivity) {
            val listSelected = ArrayList<Product>()
            for (listData in it) {
                if (productCodeSelected != null) {
                    if (listData.productCode in productCodeSelected) {
                        listSelected.add(listData)
                    }
                }
                adapter.submitData(listSelected.toList())
            }
            listSelected.forEach {
                vm.subtotalMap[it] = adapter.totalPrice(it, 1)
            }
            var sum = 0.0
            listSelected.forEach {
                sum += adapter.totalPrice(it, 1)
            }
            binding.totalPriceCheckout = sum

        }

        binding.checkoutButton.setOnClickListener {
            Toast.makeText(this@CheckoutActivity, "Berhasil di checkout", Toast.LENGTH_SHORT).show()
        }

    }

    fun getTotalPrice() {
        binding.totalPriceCheckout = vm.getSubtotal()
    }
}