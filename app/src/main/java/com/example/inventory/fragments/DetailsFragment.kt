package com.example.inventory.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.inventory.R
import com.example.inventory.databinding.FragmentDetailsBinding
import com.example.inventory.model.Item

class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding

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
        setOldData()

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    //fills fields with old data
    fun setOldData(){
        val item = arguments?.getParcelable<Item>(getString(R.string.bundleKey))
        binding.apply {
            image.setImageBitmap(item?.image)
            name.setText(item?.name)
            price.setText(item?.price.toString())
            brand.setText(item?.brand)
            quantity.setText(item?.quantity.toString())
        }
    }

    fun updateItem(){

    }

}