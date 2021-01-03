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

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import com.google.gson.Gson
import com.pri.fooddelivery.utils.Utilities.convert

@Entity(tableName = "cart_items",
        primaryKeys = ["cart_id", "user_id", "product_code"],
        foreignKeys = [
            ForeignKey(entity = Profile::class, parentColumns = ["id"], childColumns = ["user_id"])
        ])
data class CartItem(
        @ColumnInfo(name = "cart_id") val cartId: Int = (Math.random() * 9000).toInt() + 1000,
        var count: Int = 0,
        @ColumnInfo(name = "user_id") var userId: Int,
        @Embedded
        val product: Product
):Parcelable {
    override fun toString() = "${product}($count)"
    val totalPrice
        get() = product.productPrice?.let { count * product.productPrice }


    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString().convert()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(cartId)
        parcel.writeInt(count)
        parcel.writeInt(userId)
        parcel.writeString(Gson().toJson(product))
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CartItem> {
        override fun createFromParcel(parcel: Parcel): CartItem {
            return CartItem(parcel)
        }

        override fun newArray(size: Int): Array<CartItem?> {
            return arrayOfNulls(size)
        }
    }
}

