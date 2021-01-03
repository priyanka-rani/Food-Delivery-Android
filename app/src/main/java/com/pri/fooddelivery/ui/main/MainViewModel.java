package com.pri.fooddelivery.ui.main;

import android.content.Context;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.pri.fooddelivery.repository.ProductRepository;
import com.pri.fooddelivery.repository.ProfileRepository;

import java.util.List;


public class MainViewModel extends ViewModel {
    private ProductRepository productRepository;
    private ProfileRepository profileRepository;
    public LiveData<Integer> fabCounter;

    @ViewModelInject
    MainViewModel(ProfileRepository profileRepository,
                  ProductRepository productRepository) {
        this.profileRepository = profileRepository;
        this.productRepository = productRepository;
        this.fabCounter = productRepository.getAllCartItemCount();
    }

    public LiveData<List<String>> getProductCategoryList() {
        return productRepository.getProductCategoryList();
    }
    public void insertProductList(){
        profileRepository.insertProductList();
    }

    public void logoutUser() {
        profileRepository.logoutUser();
    }
}