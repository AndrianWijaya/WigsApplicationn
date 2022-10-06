package com.iglo.wigsapplicationn.activity.product


import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.iglo.wigsapplicationn.BR
import com.iglo.wigsapplicationn.R
import com.iglo.wigsapplicationn.databinding.ListProductLayoutBinding
import com.iglo.wigsapplicationn.databinding.ProductDetailLayoutBinding
import com.iglo.wigsapplicationn.view_model.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetails: AppCompatActivity() {
    val layoutResourceId = R.layout.product_detail_layout
    lateinit var binding: ProductDetailLayoutBinding

    val vm: ProductViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(this), layoutResourceId, null, false
        )
        binding.lifecycleOwner = this
        setContentView(binding.root)
        binding.setVariable(BR.vm, vm)

        vm.productCode.value = intent.getStringExtra("PRODUCT_CODE")
        vm.productCode.observe(this@ProductDetails){
            vm.getOneData(it)
        }


    }
}