package com.example.inventory.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.drawToBitmap
import androidx.navigation.fragment.findNavController
import com.example.inventory.R
import com.example.inventory.databinding.FragmentDetailsBinding
import com.example.inventory.model.Item
import com.example.inventory.presenter.presenterImpl.PresenterDetailsImpl
import com.example.inventory.view.ViewDetails

class DetailsFragment : Fragment(), ViewDetails {

    lateinit var binding: FragmentDetailsBinding

    lateinit var presenterDetailsImpl: PresenterDetailsImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = arguments?.getParcelable<Item>(getString(R.string.bundleKey))

        presenterDetailsImpl = PresenterDetailsImpl(requireContext())

        item?.let {
            setOldData(it)
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.saveItemButton.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext())
            dialog.apply {
                setTitle("Do you want to change details?")
                dialog.setPositiveButton("Yes"){ _ , _ ->
                    item?.id?.let { it1 -> updateItem(it1) }
                }
                dialog.setNegativeButton("No"){ _, _ ->
                    Toast.makeText(requireContext(), "Canceled", Toast.LENGTH_SHORT).show()
                }
            }.create()
            dialog.show()
        }

    }

    //fills fields with old data
    fun setOldData(item: Item) {
        binding.apply {
            image.setImageBitmap(item?.image)
            name.setText(item?.name)
            price.setText(item?.price.toString())
            brand.setText(item?.brand)
            quantity.setText(item?.quantity.toString())
        }
    }

    //updates item's data
    fun updateItem(id: Int){
        if(binding.image.drawable != null
            && !binding.name.text.isNullOrBlank()
            && !binding.price.text.isNullOrBlank()
            && !binding.brand.text.isNullOrBlank()
            && !binding.quantity.text.isNullOrBlank())
        {
            val image = binding.image.drawToBitmap()
            val name = binding.name.text.toString()
            val price = binding.price.text.toString().trim().toDouble()
            val brand = binding.brand.text.toString()
            val quantity = binding.quantity.text.toString().trim().toInt()
            val item = Item(id, name, price, brand, quantity, image, 0)
            presenterDetailsImpl.update(item)
            Toast.makeText(requireActivity(), getString(R.string.successfully_changed), Toast.LENGTH_SHORT ).show()
            findNavController().navigateUp()
        }else{
            Toast.makeText(requireContext(), getString(R.string.empty_fields_warning), Toast.LENGTH_SHORT).show()
        }

    }

    override fun showSuccess(successMessage: String) {
        Toast.makeText(requireContext(), successMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

}