package com.iglo.common.helper

import com.iglo.common.entity.Product

object InitialProductsDataSource {
    fun getAllProducts(): List<Product> = listOf(
        Product(
            "SKUSKILNP", "So Klin Pewangi", 15000.00, "IDR",
            10, "13 cm x 10cm", "PCS"
        ),
        Product(
            "GIBBR", "GIV Biru", 11000.00, "IDR",
            0, "5 cm x 10cm", "PCS"
        ),
        Product(
            "SKUSKILD", "So Klin Liquid", 18000.00, "IDR",
            0, "13 cm x 10cm", "PCS"
        ),
        Product(
            "GIVKNG", "GIV Kuning", 10000.00, "IDR",
            0, "5 cm x 10cm", "PCS"
        )
    )
}