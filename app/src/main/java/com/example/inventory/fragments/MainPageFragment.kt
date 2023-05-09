package com.example.inventory.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inventory.MyRecyclerViewAdapter
import com.example.inventory.R
import com.example.inventory.databinding.BottomSheetDialogBinding
import com.example.inventory.databinding.FragmentMainPageBinding
import com.example.inventory.model.Item
import com.example.inventory.presenter.presenterImpl.PresenterMainImpl
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.collections.ArrayList

class MainPageFragment : Fragment(), com.example.inventory.view.ViewMain {

    lateinit var binding: FragmentMainPageBinding
    private lateinit var presenterMainImpl: PresenterMainImpl
    private var adapter = MyRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        presenterMainImpl = PresenterMainImpl(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewMain.layoutManager = GridLayoutManager(this.context, 2)
        presenterMainImpl.attachView(this)
        presenterMainImpl.getAllItems()
        binding.recyclerViewMain.adapter = adapter
        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment_to_createNewCrossFragment)
        }

        //Click to details page
        adapter.onItemClickToDetails = {
            val bundle = Bundle()
            bundle.putParcelable(getString(R.string.bundleKey), it)
            findNavController().navigate(R.id.action_mainPageFragment_to_detailsFragment, bundle)
        }

        //searches item
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                presenterMainImpl.searchItem(newText)
                return true
            }
        })


        //archives clicked item
        adapter.actionsButton = {

            val item = it
            val dialog = BottomSheetDialog(requireContext())
            val sheetBinding = BottomSheetDialogBinding.inflate(layoutInflater)
            sheetBinding.archiveOrRestore.setText(R.string.archiveButton)
            dialog.setContentView(sheetBinding.root)
            sheetBinding.archiveOrRestore.setOnClickListener {
                archiveItem(item)
                dialog.dismiss()
            }

            sheetBinding.delete.setOnClickListener {
                val provement = AlertDialog.Builder(requireContext())
                provement.apply {
                    setTitle("You really want to delete this item?")
                    setPositiveButton("Yes") { _, _ ->
                        presenterMainImpl.delete(item)
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



    fun archiveItem(item: Item){
        val item = Item(item.id, item.name, item.price, item.brand, item.quantity, item.image, 1)
        presenterMainImpl.update(item)
    }


    override fun showAllItems(items: LiveData<List<Item>>) {
        items.observe(viewLifecycleOwner, {
                itemList -> adapter.setItemList(itemList)
        })
    }

    //searchs item
    override fun searchItem(query: String, items: LiveData<List<Item>>) {
        items.observe(viewLifecycleOwner, { itemsList ->
            if(query != null){
                val filtered = ArrayList<Item>()
                for (i in itemsList){
                    if (i.name.lowercase().contains(query.lowercase()) || i.brand.lowercase().contains(query.lowercase())){
                        filtered.add(i)
                    }
                }
                if (!itemsList.isEmpty() && filtered.isEmpty()){
                    Toast.makeText(requireContext(), "Item is not found", Toast.LENGTH_SHORT).show()
                }else{
                    adapter.setItemList(filtered)
                }
            }
        })
    }


    override fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun showSuccess(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


}