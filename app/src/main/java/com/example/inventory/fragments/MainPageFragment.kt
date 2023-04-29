package com.example.inventory.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inventory.R
import com.example.inventory.databinding.FragmentMainPageBinding

class MainPageFragment : Fragment() {

    lateinit var binding: FragmentMainPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewMain.layoutManager = GridLayoutManager(this.context, 2)
//        binding.recyclerViewMain.adapter = MyRecyclerViewAdapter()

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment_to_createNewCrossFragment)
        }

    }

}