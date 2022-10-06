package com.iglo.service.use_case

import com.iglo.common.entity.Product
import kotlinx.coroutines.flow.flow


class ProductUseCase {
    operator fun invoke() = flow {
        val listProductItem = arrayListOf<Product>(
            Product(
                "SKUSKILNP", "So Klin Pewangi", 15000.00, "IDR",
                10, "13 cm x 10cm", "PCS"
            ),
            Product(
                "GIBBR", "GIV Biru", 11000.00, "IDR",
                0, "13 cm x 10cm", "PCS"
            ),
            Product(
                "SKUSKILD", "So Klin Liquid", 18000.00, "IDR",
                0, "13 cm x 10cm", "PCS"
            ),
            Product(
                "GIVKNG", "GIV Kuning", 10000.00, "IDR",
                0, "13 cm x 10cm", "PCS"
            )
        )
        emit(listProductItem)
    }
}