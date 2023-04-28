package com.example.inventory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.databinding.ItemBinding
import com.example.inventory.model.Cross

class MyRecyclerViewAdapter(val crossList: ArrayList<Cross>): RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = ItemBinding.bind(itemView)
        fun bind(cross: Cross) = with(binding){
            textName.text = cross.name
            textBrand.text = cross.brand
            textPrice.text = cross.price.toString()
            textQuantity.text = cross.quantity.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(crossList[position])

    }

    override fun getItemCount(): Int {
        return  crossList.size
    }


}