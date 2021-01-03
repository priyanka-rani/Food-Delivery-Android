package com.pri.fooddelivery.ui.signup;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.pri.fooddelivery.R;
import com.pri.fooddelivery.utils.Utilities;
import com.pri.fooddelivery.databinding.ActivitySignupBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    private SignupViewModel signupViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        signupViewModel = new ViewModelProvider(this).get(SignupViewModel.class);
        binding.setViewModel(signupViewModel);
        binding.setLifecycleOwner(this);
        signupViewModel.getSignUpResponse().observe(this, success -> {
            Utilities.INSTANCE.hideKeyboard(SignupActivity.this);
            if(success)
            new AlertDialog.Builder(this)
                    .setTitle(R.string.sign_up)
                    .setMessage(R.string.signup_success_message)
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                        onBackPressed();
                    }).show();
            else Snackbar.make(binding.getRoot(), R.string.signup_failed, Snackbar.LENGTH_LONG).show();
        });
        binding.toolBar.setNavigationOnClickListener(v -> onBackPressed());
    }
}