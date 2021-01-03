package com.pri.fooddelivery.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.Worker
import com.pri.fooddelivery.api.ApiService
import com.pri.fooddelivery.utils.Utilities
import com.pri.fooddelivery.data.Profile
import com.pri.fooddelivery.data.Resource
import com.pri.fooddelivery.db.ProfileDao
import com.pri.fooddelivery.pref.PreferenceHelper
import com.pri.fooddelivery.utils.AppExecutors
import com.pri.fooddelivery.workers.CartDatabaseWorker
import dagger.hilt.android.qualifiers.ApplicationContext
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
class ProfileRepository @Inject constructor(
        @ApplicationContext private val context: Context,
        val appExecutors: AppExecutors,
        private val profileDao: ProfileDao,
        private val apiService: ApiService,
        private val preferenceHelper: PreferenceHelper) {
    fun loadUser(email: String): LiveData<Resource<Profile>> {
        return object : NetworkBoundResource<Profile, Profile>(appExecutors) {
            override fun saveCallResult(item: Profile) {
                profileDao.getProfile(email = email)
            }

            override fun shouldFetch(data: Profile?): Boolean {
                if(data == null&& email=="test@gmail.com"){
                    appExecutors.diskIO().execute {
                        CartDatabaseWorker.insertProfile(context = context)
                    }
                }
                return false
            }

            override fun loadFromDb() =
                    profileDao.getProfile(email = email)

            override fun createCall() = apiService.getUser(email)
        }.asLiveData()
    }

    fun getUser()= profileDao.getProfile(preferenceHelper.loggedInId)

    fun loginUser(loggedinId: Int) {
        preferenceHelper.loginUser(loggedinId)
    }

    fun insertUser(profile: Profile) = profileDao.insert(profile)

    suspend fun updateUser(profile: Profile)=
        profileDao.update(profile)

    fun logoutUser() {
        preferenceHelper.logoutUser()

    }
    fun insertProductList(){
        appExecutors.diskIO().execute {
            CartDatabaseWorker.insertProducts(context = context)
        }
    }
    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.

}