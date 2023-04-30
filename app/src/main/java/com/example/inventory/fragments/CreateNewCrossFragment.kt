package com.example.inventory.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RestrictTo.Scope
import androidx.core.view.drawToBitmap
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.inventory.R
import com.example.inventory.dataBase.ItemDao
import com.example.inventory.dataBase.ItemViewModel
import com.example.inventory.dataBase.Repository
import com.example.inventory.databinding.FragmentCreateNewBinding
import com.example.inventory.model.Item

class CreateNewCrossFragment : Fragment() {

    lateinit var binding: FragmentCreateNewBinding

    private lateinit var viewModel: ItemViewModel

    private val selectImageFromGalleryResult = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { previewImage.setImageURI(uri) }
    }

    private val previewImage by lazy { binding.gottenImage }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_createNewCrossFragment_to_mainPageFragment)
        }
        binding.getImage.setOnClickListener {
            selectImageFromGallery()
        }
        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        binding.addItemButton.setOnClickListener {
            insertItemToDB()
        }
        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_createNewCrossFragment_to_mainPageFragment)
        }
    }



    private fun insertItemToDB() {
        val image = binding.gottenImage.drawToBitmap()
        val name = binding.inputName.text.toString()
        val price = binding.inputPrice.text.toString()
        val brand = binding.inputBrand.text.toString()
        val quantity = binding.inputQuantity.text.toString()

        if(image!= null && name != null && price != null && brand != null && quantity != null){
            val item = Item(0, name, price.toInt(), brand, quantity.toInt(), image,false)
            viewModel.addItem(item)
            Toast.makeText(requireContext(), "Item is added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_createNewCrossFragment_to_mainPageFragment)
        }else{
            Toast.makeText(requireContext(), "Fill out all fields!!", Toast.LENGTH_SHORT).show()
        }
    }

    //selects image from phone
    private fun selectImageFromGallery() = selectImageFromGalleryResult.launch("image/*")

}