package com.iglo.wigsapplicationn.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iglo.common.entity.Product
import com.iglo.service.use_case.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    application: Application,
    val productUseCase: ProductUseCase
): AndroidViewModel(application) {

    val dataProduct = MutableLiveData<ArrayList<Product>>()
    val oneData = MutableLiveData<Product>()
    val productCode = MutableLiveData<String>()
    val selection = MutableLiveData<List<Product>>()


    init {
        getProductList()
    }

    private fun getProductList(){
        viewModelScope.launch {
            productUseCase().collect(){
                dataProduct.postValue(it)
            }
        }
    }

    fun getOneData(productCode : String){
        viewModelScope.launch {
            productUseCase().collect{
                for (listProduct: Product in it){
                    if (listProduct.productCode.equals(productCode)){
                        oneData.postValue(listProduct)
                    }
                }
            }
        }
    }
}