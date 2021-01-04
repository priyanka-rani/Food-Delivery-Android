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

package com.pri.fooddelivery.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.pri.fooddelivery.utils.Utilities
import com.pri.fooddelivery.data.Product
import com.pri.fooddelivery.data.Profile
import com.pri.fooddelivery.db.AppDatabase
import kotlinx.coroutines.coroutineScope

class CartDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            insertProfile(context = applicationContext)
            insertProducts(context = applicationContext)
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        fun insertProfile(context: Context) {
            val database = AppDatabase.getInstance(context)
            val profileDao = database.profileDao()
            profileDao.insertAsync(Profile("Test User", "test@gmail.com", Utilities.encodeToBase64("test123")))
        }
        fun insertProducts(context: Context) =
                context.assets.open(GROCERY_DATA_FILENAME).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<Product>>() {}.type
                        val productList: List<Product> = Gson().fromJson(jsonReader, type)
                        val database = AppDatabase.getInstance(context)
                        database.productDao().insertAll(productList)
                        Result.success()
                    }
                }

        private const val GROCERY_DATA_FILENAME = "products.json"
        private const val TAG = "CartDatabaseWorker"
    }
}
