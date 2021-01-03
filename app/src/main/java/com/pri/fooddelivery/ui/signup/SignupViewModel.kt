package com.pri.fooddelivery.ui.signup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.pri.fooddelivery.utils.Utilities
import com.pri.fooddelivery.data.Profile
import com.pri.fooddelivery.repository.ProfileRepository

class SignupViewModel @ViewModelInject constructor(private val profileRepository: ProfileRepository) : ViewModel() {
    var name = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var contact = MutableLiveData<String>()
    var address = MutableLiveData<String>()
    var pass = MutableLiveData<String>()
    var category = MutableLiveData<String>()
    private val _signup = MutableLiveData<Profile>()
    val signUpResponse = _signup.switchMap {
        liveData {
            val result = profileRepository.insertUser(it)
            emit(result > 0)
        }
    }

    fun insertUser() {
        val profile = Profile(name.value, email.value, pass.value?.let { Utilities.encodeToBase64(it) })
        profile.contact = contact.value
        profile.address = address.value
        profile.category = category.value

        _signup.value = profile
    }

    fun enableRegister(vararg args: String?): Boolean {
        return !args.any { it.isNullOrBlank() }
    }

}