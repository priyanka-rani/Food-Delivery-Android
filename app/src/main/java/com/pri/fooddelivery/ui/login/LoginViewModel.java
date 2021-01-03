package com.pri.fooddelivery.ui.login;

import android.text.TextUtils;
import android.util.Pair;
import android.util.Patterns;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.pri.fooddelivery.data.Profile;
import com.pri.fooddelivery.data.Resource;
import com.pri.fooddelivery.repository.ProfileRepository;


public class LoginViewModel extends ViewModel {
    private ProfileRepository profileRepository;

    private MutableLiveData<String> _login = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> pass = new MutableLiveData<>();

    @ViewModelInject
    LoginViewModel(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    LiveData<Resource<Profile>> profileLiveData =
            Transformations.switchMap(_login, input ->
                    profileRepository.loadUser(input)
            );

    public void loginUser() {
        _login.postValue(email.getValue());
    }

    void saveLogin(int loggedinId) {
        profileRepository.loginUser(loggedinId);
    }


    public boolean enableLogin(String email, String pass) {
        return !(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}