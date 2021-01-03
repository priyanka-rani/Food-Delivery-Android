package com.pri.fooddelivery.ui.order

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.pri.fooddelivery.R
import com.pri.fooddelivery.databinding.ActivityOrderBinding
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    private lateinit var orderViewModel: OrderViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order)
        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        binding.viewModel = orderViewModel
        binding.lifecycleOwner = this
        orderViewModel.orderList.observe(this, Observer {
            it?.let { data ->
                val list = data.mapNotNull {
                    val key = Gson().toJson(it)
                    ExpandableGroup(key, it.cartItemList)
                }
                val adapter = OrderAdapter()
                binding.rvItems.adapter = adapter
                adapter.setGroups(list)

            }
        })
        binding.toolBar.setNavigationOnClickListener { v: View? -> onBackPressed() }
    }
}