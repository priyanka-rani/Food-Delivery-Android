/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pri.fooddelivery.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "products")
data class Product(
        @PrimaryKey @ColumnInfo(name = "product_code") @SerializedName("product_code") val productCode: Int,
        @ColumnInfo(name = "product_name") @SerializedName("product_name") val productName: String?,
        @ColumnInfo(name = "product_description") @SerializedName("product_description") val productDescription: String?,
        @ColumnInfo(name = "product_price") @SerializedName("product_price") val productPrice: Double?,
        @ColumnInfo(name = "product_quantity") @SerializedName("product_quantity") val productQuantity: String?,
        @ColumnInfo(name = "product_category") @SerializedName("product_category") val productCategory: String?
) {
    override fun toString() = productName.toString()
    val imageName
        get() = "prod_${productCode}"
}
