package com.pri.fooddelivery.ui.order;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pri.fooddelivery.data.OrderItem;
import com.pri.fooddelivery.repository.ProductRepository;

import java.util.List;

public class OrderViewModel extends ViewModel {
    LiveData<List<OrderItem>> orderList;
    @ViewModelInject
    public OrderViewModel(ProductRepository productRepository){
        orderList = productRepository.getAllOrderItem();

    }
}
