package com.example.inventory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemSelectedListener
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.databinding.ItemBinding
import com.example.inventory.model.Item

class MyRecyclerViewAdapter: RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {

    private var itemList: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return  itemList.size
    }

    fun setItemList(items: List<Item>){
        this.itemList = items
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = ItemBinding.bind(itemView)
        fun bind(item: Item) = with(binding){
//            image.setImageBitmap(item.image)
            textName.text = item.name
            textBrand.text = item.brand
            textPrice.text = item.price.toString()
            textQuantity.text = item.quantity.toString()
        }
    }

}