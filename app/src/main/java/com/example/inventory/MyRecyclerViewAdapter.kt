package com.example.inventory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.databinding.ItemBinding
import com.example.inventory.model.Item

class MyRecyclerViewAdapter: RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {

    private var itemList: List<Item> = emptyList()

    var onItemClickToDetails: ((Item) -> Unit)? = null

    var actionsButton: ((Item) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position], position, itemList.size)
        holder.binding.apply {
            textName.setOnClickListener {  onItemClickToDetails?.invoke(itemList[position]) }
            image.setOnClickListener { onItemClickToDetails?.invoke(itemList[position]) }
        }
        //calls sheet buttons
        holder.binding.actionsButton.setOnClickListener{
            actionsButton?.invoke(itemList[position])
        }

    }

    override fun getItemCount(): Int {
        return  itemList.size
    }

    fun setItemList(newItems: List<Item>){
        val diffUtil = MyDiffUtils(itemList, newItems)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        this.itemList = newItems
        diffUtilResult.dispatchUpdatesTo(this)
//        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = ItemBinding.bind(itemView)

        fun bind(item: Item, position: Int, size: Int) = with(binding){
            image.setImageBitmap(item.image)
            textName.text = item.name
            textBrand.text = item.brand
            textPrice.text = item.price.toString()
            textQuantity.text = item.quantity.toString()

            val layoutParams = itemView.layoutParams as RecyclerView.LayoutParams
            if(position == size-1 && size % 2 != 0){
                layoutParams.bottomMargin = 50
            }else if (position == size-1 || position == size-2 && size % 2 == 0){
                layoutParams.bottomMargin = 50
            }else{
                layoutParams.bottomMargin = 0
            }
        }
    }

}