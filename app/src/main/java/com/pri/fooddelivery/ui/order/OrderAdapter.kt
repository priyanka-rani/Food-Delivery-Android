package com.pri.fooddelivery.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pri.fooddelivery.utils.Utilities.convert
import com.pri.fooddelivery.data.CartItem
import com.pri.fooddelivery.databinding.ItemChildOrderLayoutBinding
import com.pri.fooddelivery.databinding.ItemGroupOrderLayoutBinding
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.listeners.GroupExpandCollapseListener
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup


class OrderAdapter()
    : ExpandableRecyclerViewAdapter<GroupLimitViewHolder, ChildLimitViewHolder>(emptyList()) {
    private var expandedGroup: ExpandableGroup<*>? = null

    init {
        setOnGroupExpandCollapseListener(object : GroupExpandCollapseListener {
            override fun onGroupExpanded(group: ExpandableGroup<*>) {
                try {
                    if (expandedGroup != null &&
                            expandedGroup != group &&
                            isGroupExpanded(expandedGroup)) {
                        toggleGroup(expandedGroup)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                expandedGroup = group
            }

            override fun onGroupCollapsed(group: ExpandableGroup<*>?) {}
        })
    }

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): GroupLimitViewHolder {
        val groupLimitBinding = ItemGroupOrderLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false)
        return GroupLimitViewHolder(groupLimitBinding)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): ChildLimitViewHolder {
        val childLimitBinding = ItemChildOrderLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent,false)
        return ChildLimitViewHolder(childLimitBinding)
    }

    override fun onBindChildViewHolder(holder: ChildLimitViewHolder?, flatPosition: Int,
                                       group: ExpandableGroup<*>?, childIndex: Int) {
        val txLimit = group?.items?.get(childIndex) as? CartItem
        holder?.setChildLimitData(txLimit)
    }

    override fun onBindGroupViewHolder(holder: GroupLimitViewHolder?, flatPosition: Int,
                                       group: ExpandableGroup<*>?) {
        holder?.setGroupLimitData(group?.title.convert())
    }

    fun setGroups(groups: List<ExpandableGroup<CartItem>>) {
        expandableList.groups = groups
        expandableList.expandedGroupIndexes = BooleanArray(groups.size)
        for (i in groups.indices) {
            expandableList.expandedGroupIndexes[i] = false
        }
        notifyDataSetChanged()
    }

}
