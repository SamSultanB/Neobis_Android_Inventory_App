package com.example.inventory.fragments

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.drawToBitmap
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.example.inventory.databinding.FragmentCreateNewBinding
import com.example.inventory.model.Item
import com.example.inventory.presenter.PresenterImpl

class CreateNewCrossFragment : Fragment(), com.example.inventory.view.View {

    lateinit var binding: FragmentCreateNewBinding

    lateinit var presenterImpl: PresenterImpl

    private val selectImageFromGalleryResult = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
            previewImage.setImageBitmap(bitmap)
        }
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
        presenterImpl = PresenterImpl(requireContext())

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.getImage.setOnClickListener {
            selectImageFromGallery()
        }
        binding.addItemButton.setOnClickListener {
            insertItemToDB()
        }
        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun insertItemToDB() {
        val image = binding.gottenImage.drawToBitmap()
        val name = binding.inputName.text.toString()
        val price = binding.inputPrice.text.toString().trim().toDouble()
        val brand = binding.inputBrand.text.toString()
        val quantity = binding.inputQuantity.text.toString().trim().toInt()
        val item = Item(0, name, price, brand, quantity, image, false)
        presenterImpl.addItem(item)
        Toast.makeText(requireContext(), "Item is added!", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }

    //selects image from phone
    private fun selectImageFromGallery() = selectImageFromGalleryResult.launch("image/*")



    override fun showAllItems(items: LiveData<List<Item>>) {
        TODO("Not yet implemented")
    }

    override fun showError(error: String) {
        TODO("Not yet implemented")
    }

}