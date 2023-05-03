package com.example.inventory.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.drawToBitmap
import androidx.navigation.fragment.findNavController
import com.example.inventory.R
import com.example.inventory.databinding.FragmentDetailsBinding
import com.example.inventory.model.Item
import com.example.inventory.presenter.PresenterDetailsImpl
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

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.saveItemButton.setOnClickListener {
            item?.id?.let { it1 -> updateItem(it1) }
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

    fun updateItem(id: Int){
        val image = binding.image.drawToBitmap()
        val name = binding.name.text.toString()
        val price = binding.price.text.toString().trim().toDouble()
        val brand = binding.brand.text.toString()
        val quantity = binding.quantity.text.toString().trim().toInt()
        val item = Item(id, name, price, brand, quantity, image, 1)
        presenterDetailsImpl.update(item)
        findNavController().navigateUp()

    }

    override fun showSuccess(successMessage: String) {
        TODO("Not yet implemented")
    }

    override fun showError(errorMessage: String) {
        TODO("Not yet implemented")
    }

}