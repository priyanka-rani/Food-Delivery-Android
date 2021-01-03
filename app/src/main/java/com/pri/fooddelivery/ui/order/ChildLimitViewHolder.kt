package com.pri.fooddelivery.ui.order

import com.pri.fooddelivery.data.CartItem
import com.pri.fooddelivery.databinding.ItemChildOrderLayoutBinding
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder

class ChildLimitViewHolder(private val itemChildBinding: ItemChildOrderLayoutBinding)
    : ChildViewHolder(itemChildBinding.root) {
    fun setChildLimitData(data: CartItem?) {
        itemChildBinding.data = data
    }
}