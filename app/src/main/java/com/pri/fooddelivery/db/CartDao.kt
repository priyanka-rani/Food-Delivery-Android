package com.pri.fooddelivery.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pri.fooddelivery.data.CartItem

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ /**
 * The Room Magic is in this file, where you map a Java method call to an SQL query.
 *
 *
 * When you are using complex data types, such as Date, you have to also supply type converters.
 * To keep this example basic, no types that require type converters are used.
 * See the documentation at
 * https://developer.android.com/topic/libraries/architecture/room.html#type-converters
 */
@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items where user_id=:userId")
    fun getAll(userId: Int): LiveData<List<CartItem>>

    @Query("SELECT SUM(count) FROM cart_items where user_id=:userId")
    fun getCartItemCounter(userId: Int): LiveData<Int>

    @Query("UPDATE cart_items SET count = count + 1 WHERE user_id=:userId AND product_code = :productCode")
    fun incrementItemCounter(userId: Int, productCode: Int): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cartItem: CartItem?)

    @Update
    fun update(cartItem: CartItem?)

    @Delete
    fun delete(cartItem: CartItem?)

    @Transaction
    fun increOrInsert(cartItem: CartItem) {
        if (incrementItemCounter(cartItem.userId, cartItem.product.productCode) <= 0) {
            cartItem.count = 1
            insert(cartItem)
        }
    }

    @Query("DELETE FROM cart_items")
    fun deleteAll()
}