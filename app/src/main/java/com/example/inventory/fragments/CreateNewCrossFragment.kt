package com.example.inventory.fragments

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.drawToBitmap
import androidx.navigation.fragment.findNavController
import com.example.inventory.R
import com.example.inventory.databinding.FragmentCreateNewBinding
import com.example.inventory.model.Item
import com.example.inventory.presenter.presenterImpl.PresenterNewItemImpl
import com.example.inventory.view.ViewNewItem

class CreateNewCrossFragment : Fragment(), ViewNewItem {

    lateinit var binding: FragmentCreateNewBinding

    lateinit var presenterNewItemImpl: PresenterNewItemImpl

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
        presenterNewItemImpl = PresenterNewItemImpl(requireContext())

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
        if(binding.gottenImage.drawable != null
            && !binding.inputName.text.isNullOrBlank()
            && !binding.inputPrice.text.isNullOrBlank()
            && !binding.inputBrand.text.isNullOrBlank()
            && !binding.inputQuantity.text.isNullOrBlank())
        {
            val image = binding.gottenImage.drawToBitmap()
            val name = binding.inputName.text.toString()
            val price = binding.inputPrice.text.toString().trim().toDouble()
            val brand = binding.inputBrand.text.toString()
            val quantity = binding.inputQuantity.text.toString().trim().toInt()
            val item = Item(0, name, price, brand, quantity, image, 0)
            presenterNewItemImpl.addItem(item)
            findNavController().navigateUp()
        }else{
            Toast.makeText(requireContext(), getString(R.string.empty_fields_warning), Toast.LENGTH_SHORT).show()
        }


    }

    //selects image from phone
    private fun selectImageFromGallery() = selectImageFromGalleryResult.launch("image/*")


    override fun showSuccess(successMessage: String) {
        Toast.makeText(requireContext(), successMessage, Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }


}