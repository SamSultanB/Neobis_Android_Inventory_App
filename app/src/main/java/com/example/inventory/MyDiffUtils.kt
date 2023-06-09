package com.example.inventory

import androidx.recyclerview.widget.DiffUtil
import com.example.inventory.model.Item

class MyDiffUtils(
    private val oldList: List<Item>,
    private val newList: List<Item>
    ): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].name != newList[newItemPosition].name -> false
            oldList[oldItemPosition].price != newList[newItemPosition].price -> false
            oldList[oldItemPosition].brand != newList[newItemPosition].brand -> false
            oldList[oldItemPosition].quantity != newList[newItemPosition].quantity -> false
            oldList[oldItemPosition].image != newList[newItemPosition].image -> false
            oldList[oldItemPosition].archived != newList[newItemPosition].archived -> false
            else -> true
        }
    }
}