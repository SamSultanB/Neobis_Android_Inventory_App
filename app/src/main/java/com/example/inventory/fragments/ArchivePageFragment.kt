package com.example.inventory.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inventory.MyRecyclerViewAdapter
import com.example.inventory.R
import com.example.inventory.databinding.BottomSheetDialogBinding
import com.example.inventory.databinding.FragmentArchivePageBinding
import com.example.inventory.model.Item
import com.example.inventory.presenter.presenterImpl.PresenterArchiveImpl
import com.example.inventory.view.ViewArchive
import com.google.android.material.bottomsheet.BottomSheetDialog

class ArchivePageFragment : Fragment(), ViewArchive {

    lateinit var binding: FragmentArchivePageBinding
    lateinit var presenterArchiveImpl: PresenterArchiveImpl
    private var adapter = MyRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        presenterArchiveImpl = PresenterArchiveImpl(requireContext())
        binding = FragmentArchivePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewArchive.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerViewArchive.adapter = adapter
        presenterArchiveImpl.attachView(this)
        presenterArchiveImpl.getAllArchived()

        //calls search method
        binding.searchViewArchive.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenterArchiveImpl.searchItem(newText)
                return true
            }
        })

        adapter.actionsButton = {
            val item = it
            val dialog = BottomSheetDialog(requireContext())
            val sheetBinding = BottomSheetDialogBinding.inflate(layoutInflater)
            sheetBinding.archiveOrRestore.setText(R.string.restore)
            dialog.setContentView(sheetBinding.root)
            sheetBinding.archiveOrRestore.setOnClickListener {
                restoreItem(item)
                dialog.dismiss()
            }
            sheetBinding.delete.setOnClickListener {
                val provement = AlertDialog.Builder(requireContext())
                provement.apply {
                    setTitle(R.string.deleteItem)
                    setPositiveButton("Yes") { _, _ ->
                        presenterArchiveImpl.delete(item)
                        dialog.dismiss()
                    }
                    setNegativeButton("No"){ _, _ ->
                        dialog.dismiss()
                    }
                }.create()
                provement.show()
            }
            dialog.show()
        }

    }

    fun restoreItem(item: Item) {
        val item = Item(item.id, item.name, item.price, item.brand, item.quantity, item.image, 0)
        presenterArchiveImpl.update(item)
    }

    override fun showAllArchived(items: LiveData<List<Item>>) {
        items.observe(viewLifecycleOwner, {itemList -> adapter.setItemList(itemList)})
    }

    override fun searchItem(query: String, items: LiveData<List<Item>>) {
        items.observe(viewLifecycleOwner, { listItems ->
            if(query != null){
                val filtered = ArrayList<Item>()
                for (i in listItems){
                    if (i.name.lowercase().contains(query.lowercase()) || i.brand.lowercase().contains(query.lowercase())){
                        filtered.add(i)
                    }
                }
                if (filtered.isEmpty()){
                    Toast.makeText(requireContext(), "Item is not found", Toast.LENGTH_SHORT).show()
                }else{
                    adapter.setItemList(filtered)
                }
            }
        })
    }

    override fun showSuccess(successMessage: String) {
        Toast.makeText(requireContext(), successMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

}