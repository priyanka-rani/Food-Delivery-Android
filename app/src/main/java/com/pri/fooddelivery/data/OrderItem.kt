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
import androidx.room.ForeignKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "order_items",
        primaryKeys = ["user_id", "order_id"],
        foreignKeys = [
            ForeignKey(entity = Profile::class, parentColumns = ["id"], childColumns = ["user_id"])
        ])
data class OrderItem(
        @ColumnInfo(name = "user_id") var userId: Int = -1,
        @ColumnInfo(name = "order_id") val orderId: Int = (Math.random() * 9000).toInt() + 1000,
        val totalPrice: Double?,
        val date: String = SimpleDateFormat("dd/M/yyyy hh:mm a").format(Calendar.getInstance().time),
        val cartItemList: List<CartItem>?
) {
    override fun toString() = cartItemList?.joinToString().toString()
}

