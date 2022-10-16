package com.iglo.common.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Products")
data class Product(
    @PrimaryKey
    @field:ColumnInfo(name = "ProductCode")
    val productCode: String,

    @field:ColumnInfo(name = "ProductName")
    val productName: String,

    @field:ColumnInfo(name = "Price")
    val price: Double,

    @field:ColumnInfo(name = "Currency")
    val currency: String,

    @field:ColumnInfo(name = "Discount")
    val discount: Int,

    @field:ColumnInfo(name = "Dimension")
    val dimension: String,

    @field:ColumnInfo(name = "Unit")
    val unit: String
) : Parcelable