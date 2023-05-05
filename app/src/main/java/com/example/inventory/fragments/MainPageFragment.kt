package com.example.inventory.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        //archives clicked item
        adapter.actionsButton = {
            val item = it
            val dialog = BottomSheetDialog(requireContext())
            val sheetBinding = BottomSheetDialogBinding.inflate(layoutInflater)
            sheetBinding.archive.setText(R.string.archiveButton)
            dialog.setContentView(sheetBinding.root)
            sheetBinding.archive.setOnClickListener {
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
        items.observe(viewLifecycleOwner, {itemList -> adapter.setItemList(itemList)})
    }

    override fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun showSuccess(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


}