package com.pri.fooddelivery.ui.order

import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.pri.fooddelivery.data.OrderItem
import com.pri.fooddelivery.databinding.ItemGroupOrderLayoutBinding
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder

class GroupLimitViewHolder(private val itemGroupBinding: ItemGroupOrderLayoutBinding)
    : GroupViewHolder(itemGroupBinding.root) {
    fun setGroupLimitData(data:OrderItem) {
        itemGroupBinding.data = data
    }

    override fun expand() {
        animateExpand()
    }

    override fun collapse() {
        animateCollapse()
    }

    private fun animateExpand() {
        val rotate = RotateAnimation(360.0f, 180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.duration = 300
        rotate.fillAfter = true
        itemGroupBinding.listItemGenreArrow.animation = rotate
    }

    private fun animateCollapse() {
        val rotate = RotateAnimation(180.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.duration = 300
        rotate.fillAfter = true
        itemGroupBinding.listItemGenreArrow.animation = rotate
    }

}