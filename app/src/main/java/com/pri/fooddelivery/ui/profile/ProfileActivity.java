package com.pri.fooddelivery.ui.profile;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.pri.fooddelivery.R;
import com.pri.fooddelivery.utils.Utilities;
import com.pri.fooddelivery.databinding.ActivityProfileBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private ProfileViewModel profileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding.setViewModel(profileViewModel);
        binding.setLifecycleOwner(this);
        profileViewModel.getUpdateResponse().observe(this, success -> {
            Utilities.INSTANCE.hideKeyboard(ProfileActivity.this);
            new AlertDialog.Builder(this)
                    .setTitle(R.string.profile_update)
                    .setMessage(success?R.string.profile_update_message:R.string.profile_update_failed)
                    .setPositiveButton(android.R.string.ok,(dialog, which) -> {
                        if(success) onBackPressed();
                    }).show();
        });
        binding.toolBar.setNavigationOnClickListener(v -> onBackPressed());
        profileViewModel.getProfileLiveData().observe(this, profile -> {
            profileViewModel.getName().postValue(profile.name);
            profileViewModel.getEmail().postValue(profile.email);
            profileViewModel.getContact().postValue(profile.contact);
            profileViewModel.getAddress().postValue(profile.address);
            profileViewModel.getCategory().postValue(profile.category);
            profileViewModel.getPass().postValue(Utilities.INSTANCE.decodeBase64(profile.password));
        });
    }
}