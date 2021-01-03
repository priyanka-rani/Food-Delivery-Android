package com.myapp.grocerli.repository

import androidx.lifecycle.LiveData
import com.myapp.grocerli.data.CartItem
import com.myapp.grocerli.data.OrderItem
import com.myapp.grocerli.data.Product
import com.myapp.grocerli.db.AppDatabase
import com.myapp.grocerli.db.CartDao
import com.myapp.grocerli.db.OrderDao
import com.myapp.grocerli.db.ProductDao
import com.myapp.grocerli.pref.PreferenceHelper
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

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
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
@Singleton
class ProductRepository @Inject constructor(
        private val productDao: ProductDao,
        private val cartDao: CartDao,
        private val orderDao: OrderDao,
        private val preferenceHelper: PreferenceHelper) {
    fun getProductCategoryList() = productDao.getProductCategoryList()
    fun getProductsByCategory(category: String) = productDao.getProductsByCategory(category)
    fun getAllCartItem(): LiveData<List<CartItem>> = cartDao.getAll(preferenceHelper.loggedInId)
    fun getAllCartItemCount() = cartDao.getCartItemCounter(preferenceHelper.loggedInId)
    fun insertCartItem(product:Product) {
        val cartItem = CartItem(product = product, userId = preferenceHelper.loggedInId)
        cartDao.increOrInsert(cartItem)
    }

    fun updateCartItem(cartItem: CartItem) = cartDao.update(cartItem)
    fun deleteCartItem(cartItem: CartItem) = cartDao.delete(cartItem)
    fun deleteAllCartItem() = cartDao.deleteAll()
    fun getAllOrderItem() = orderDao.getAll(preferenceHelper.loggedInId)
    fun insertOrderItem(orderItem: OrderItem) {
        orderItem.userId = preferenceHelper.loggedInId
        orderDao.insert(orderItem)
    }
}