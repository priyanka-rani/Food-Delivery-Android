package com.pri.fooddelivery.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pri.fooddelivery.data.Product
import com.pri.fooddelivery.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PageViewModel @ViewModelInject constructor(private val productRepository: ProductRepository) : ViewModel() {
    private val _category = MutableLiveData<String>()
    val productList = Transformations.switchMap(_category
    ) {productRepository.getProductsByCategory(it) }

    fun setCategory(category: String) {
        _category.value = category
    }

    fun addProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO){
            productRepository.insertCartItem(product)
        }

    }

}